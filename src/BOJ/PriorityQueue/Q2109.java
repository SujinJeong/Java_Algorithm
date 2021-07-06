package BOJ.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2109 {
    static class Info implements Comparable<Info> {
        int day, cost;

        public Info(int cost, int day) {
            this.day = day;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            // 무조건 돈을 많이 벌어야 하므로 cost 순 정렬
            return o.cost - this.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Info> pq = new PriorityQueue<>();
        int[] days = new int[10001];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            pq.add(new Info(cost, day));
        }

        int total = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            // 현재 날짜가 차있다면 마지막 날부터 비어있는 날짜 탐색
            if (days[cur.day] > 0) {
                for (int i = cur.day-1; i >= 1; i--) {
                    if (days[i] == 0) {
                        days[i] = cur.cost;
                        break;
                    }
                }
            }
            else {
                // 현재 날짜 비어 있다면 삽입
                days[cur.day] = cur.cost;
            }

        }

        for (int i = 1; i <= 10000; i++)
            total += days[i];

        System.out.println(total);
    }
}
