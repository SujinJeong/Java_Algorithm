package Programmers.Kakao_2021_Intern;

import java.util.PriorityQueue;

public class Q4 {
    public static void main(String[] args) {
        System.out.println(solution(4, 1, 4, new int[][]{{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[]{2, 3}));
    }

    private static class Info implements Comparable<Info> {
        int node;
        int dist;

        public Info(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return this.dist - o.dist;
        }
    }

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int[] dist = new int[n + 1];
        boolean[] trap = new boolean[n + 1];
        int[][] adj = new int[n + 1][n + 1];
        int INF = 987654321;

        for (int i = 1; i <= n; i++) {
            dist[i] = INF;
        }

        for(int t : traps) trap[t] = true;

        for(int i = 0; i < roads.length; i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            int w = roads[i][2];

            if (adj[s][e] == 0) adj[s][e] = w;
                //길이 여러개 존재할 수 있기 때문에
            else adj[s][e] = Math.min(adj[s][e], w);
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Info(start, 0));
        int answer = INF;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            int cur_node = cur.node;
            int cur_dist = cur.dist;

            if(cur_node == end) { // 도착점 도착
                answer = Math.min(answer, cur_dist);
                break;
            }

            for(int next = 1; next <= n; next++) {
                if(adj[cur_node][next] != 0) { // 연결되어있으면
                    int next_dist = cur_dist + adj[cur_node][next];
                    if(trap[next]) {
                        // 돌린적 있는지 저장
                        for(int i = 1; i <= n; i++) {
                            // 양쪽 다 연결된 경우
                            if(adj[i][next] > 0 && adj[next][i] > 0) {
                                int tmp1 = adj[i][next];
                                int tmp2 = adj[next][i];
                                adj[next][i] = tmp1;
                                adj[i][next] = tmp2;
                            } else if (adj[i][next] > 0 || adj[next][i] > 0) { // 둘 중 하나만
                                int tmp = adj[next][i];
                                adj[next][i] = adj[i][next];
                                adj[i][next] = tmp;
                            }
                        }

                        pq.add(new Info(next, next_dist));
                    }// 트랩이 아닐때는 최소비용 계산
                        if(dist[next] > next_dist) {
                            dist[next] = next_dist;
                            pq.add(new Info(next, next_dist));
                        }
                }
            }
        }

        return answer;
    }
}