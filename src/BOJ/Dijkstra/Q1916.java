package BOJ.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1916 {
    static int n, m, INF = 987654321;
    static int[] dist;
    static ArrayList<Info>[] adj;

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

        // 정점 사이의 간선이 2개 이상 있을 수 있으므로 처리했는지 확인
        boolean[] visited = new boolean[n+1];
        
        pq.offer(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            // 방문하지 않았던 정점일 경우에만 재방문
            if (!visited[cur.des]) {
                visited[cur.des] = true;

                for (int i = 0; i < adj[cur.des].size(); i++) {
                    Info next = adj[cur.des].get(i);

                    if (cur.cost >= dist[next.des]) continue;

                    if (dist[next.des] > dist[cur.des] + next.cost) {
                        dist[next.des] = dist[cur.des] + next.cost;
                        pq.offer(new Info(next.des, dist[next.des]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dist[i] = INF;
        }

        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[s].add(new Info(e, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijstra(start);

        System.out.println(dist[end]);

    }


}
