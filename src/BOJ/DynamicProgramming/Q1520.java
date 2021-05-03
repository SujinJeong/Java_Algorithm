package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1520 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m;
    static int[][] map, dp;

    public static class Info implements Comparable<Info> {
        int x, y, h;

        public Info(int x, int y, int h) {
            super();
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public int compareTo(Info o) {
            return o.h - this.h;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m]; // 해당 지점까지 경로의 수가 저장되어있는 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(dp[n-1][m-1]);
    }

    private static void bfs() {
        // 내리막길이므로 높은값 -> 낮은 값으로 찾아야함
        PriorityQueue<Info> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][m];

        // 0,0부터 출발
        q.offer(new Info(0, 0, map[0][0]));
        visited[0][0] = true;
        dp[0][0] = 1;
        
        while (!q.isEmpty()) {
            Info cur = q.poll();

            // 4방탐색
            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + cur.x;
                int ny = dy[d] + cur.y;

                // map을 벗어났는지 검사
                if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                // 오르막일경우 검사
                if (map[nx][ny] >= map[cur.x][cur.y]) continue;

                // 들릴때마다 경로의 수 값 플러스, 들렸던 적 있던 곳도 처리 필요 하기 때문에 방문처리 전에
                dp[nx][ny] += dp[cur.x][cur.y];

                // 방문한 경우 방문처리 해줄 필요 없음
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new Info(nx, ny, map[nx][ny]));
            }
        }
        return;
    }

}
