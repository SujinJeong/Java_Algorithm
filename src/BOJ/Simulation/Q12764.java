package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q12764 {
    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if (o.start == this.start)
                return this.end - o.end;
            return this.start - o.start;
        }
    }

    static class Using implements Comparable<Using> {
        int start, end, index;

        public Using(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }

        @Override
        public int compareTo(Using o) {
            // 만약 사용하고 있는 컴퓨터의 끝나는 시간이 같다면 번호가 빠른 순으로
            if (this.end == o.end)
                return this.index - o.index;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Info[] infos = new Info[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            infos[i] = new Info(start, end);
        }

        // 시작시간 순으로 정렬
        Arrays.sort(infos);

        // pc 한대당 몇번 사용했는지 배열
        int[] pcCount = new int[100001];
        // 사용중인 컴퓨터
        PriorityQueue<Using> usingComputer = new PriorityQueue<Using>();
        // 사용가능한 컴퓨터(가장 적은 번호부터 사용하게 해야하므로 pq 사용)
        PriorityQueue<Integer> availableComputer = new PriorityQueue<Integer>();
        // 총 pc 개수
        int cnt = 0;

        for (Info cur : infos) {
            // 사용이 끝난 컴퓨터 사용 가능한 컴퓨터 만들어주기
            // while문이 아닌 if문일 경우 사용가능한 컴퓨터를 모두 빼주지않음
            while (!usingComputer.isEmpty() && cur.start > usingComputer.peek().end) {
                availableComputer.add(usingComputer.poll().index);// 사용중인 컴퓨터에서 제거
            }
            
            // 사용 가능한 컴퓨터가 있으면
            if (!availableComputer.isEmpty()) {
                int pc_num = availableComputer.poll();
                // 해당 pc 사용횟수 증가
                pcCount[pc_num]++;
                // 사용중인 컴퓨터 정보 입력
                usingComputer.add(new Using(cur.start, cur.end, pc_num));
            } else { // 사용가능한 컴퓨터가 없을 때 새로운 컴퓨터에 사용자 추가
                cnt++; //  pc 추가,
                pcCount[cnt]++;  // pc사용횟수 추가
                usingComputer.add(new Using(cur.start, cur.end, cnt));
            }
        }
 //       시간초과 코드
//        while (!pq.isEmpty()) {
//            Info cur = pq.poll();
//            boolean isValid = false;
//
//            // 가장 작은 번호의 컴퓨터 이용해야하므로 처음부터 시작
//            for (int i = 0; i < computer.size(); i++) {
//                if (computer.get(i) >= cur.end) { // 끝나는 시간이 현재 다음 컴퓨터 이용 시작 시간 보다 작으면 앞쪽에 끼어넣을 수 있음
//                    // 컴퓨터 이용 시작 시간 갱신
//                    computer.set(i, cur.start);
//                    cnt[i]++;
//                    isValid = true;
//                    break;
//                }
//            }
//
//            // 끼워넣을 수 있는 곳 못찾음
//            if (!isValid) {
//                computer.add(cur.start);
//                cnt[computer.size()-1]++;
//            }
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt+"\n");
        for (int i = 1; i <= cnt; i++) {
            sb.append(pcCount[i] + " ");
        }

        System.out.println(sb);
    }
}
