package BOJ.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q10282 {
    static int ans_cnt, ans_dist, n, INF = 987654321;
    static ArrayList<Info>[] time;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (tc-->0) {
            ans_cnt = 0;
            ans_dist = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            dist = new int[n+1];

            for (int i = 1; i <= n; i++) {
                dist[i] = INF;
            }

            time = new ArrayList[n+1];
            for (int i = 1; i <= n; i++)
                time[i] = new ArrayList<>();

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                time[from].add(new Info(to, cost));
            }

            dijstra(s);

            for (int i = 1; i <= n; i++) {
                // �����������, �� �������� ���� ��� ����
                if (dist[i] != INF) {
                    ans_cnt++;
                    ans_dist = Math.max(ans_dist, dist[i]);
                }
            }

            sb.append(ans_cnt+" "+ans_dist+"\n");
        }

        System.out.println(sb);

    }

    public static class Info implements Comparable<Info> {
        int des, cost;

        public Info(int des, int cost) {
            this.des = des;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }

    private static void dijstra(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            for (int i = 0; i < time[cur.des].size(); i++) {
                Info next = time[cur.des].get(i);

                // ���� ���� �̹� dist�� �ִ� ������ ũ�� �� �� �ʿ� ����
                if (cur.cost > dist[next.des]) continue;

                // ���� ����� �ּҰ� > ��������� �ּҰ� + �����忡�� ��������� ��
                if (dist[next.des] > dist[cur.des] + next.cost) {
                    dist[next.des] = dist[cur.des] + next.cost;
                    pq.offer(new Info(next.des, dist[next.des]));
                }
            }
        }
    }
}
