package BOJ.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q11000 {
    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 시작 시간이 가장 빠른걸로, 같다면 종료시간이 가장 빠른걸로
        @Override
        public int compareTo(Info o) {
            if (o.start == this.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        Info[] arr = new Info[n];

        // input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 주어진 조건대로 소트
        Arrays.sort(arr);

        // 종료 시간 기준 새로운 강의실에 배정해야할지 판단
        pq.add(arr[0].end);

        // sol
        for (int i = 1; i < n; i++) {
            // 끝나는 시간보다 시작 시간이 뒤면 새로운 강의실 필요없음. 마지막 수업 끝나는 시간으로 갱신.
            if (pq.peek() <= arr[i].start) {
                pq.poll();
            }
            // 새로운 강의실 배정해야할 경우
            pq.add(arr[i].end);
        }

        System.out.println(pq.size());
    }
}
