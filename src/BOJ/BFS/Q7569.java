package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7569 {
    static int[][][] map;
    static boolean[][][] visited;
    static int N, M, H, ans;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dh = {1, -1};
    static Queue<Info> q = new LinkedList<>();

    static class Info {
        int h, n, m;

        public Info(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    map[h][n][m] = Integer.parseInt(st.nextToken());
                    if (map[h][n][m] == 1) {
                        q.offer(new Info(h, n, m));
                        visited[h][n][m] = true;
                    }
                }
            }
        }

        bfs();

        if (check()) sb.append(ans-1);
        else sb.append(-1);

        System.out.println(sb);
    }

    private static boolean check() {
        boolean flag = true;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[h][n][m] == 0) {
                        flag = false;
                    }
                    ans = Math.max(ans, map[h][n][m]);
                }
            }
        }

        return flag;
    }

    private static void bfs() {

        while (!q.isEmpty()) {

            Info i = q.poll();
            int cur = map[i.h][i.n][i.m];
            // 4¹æÅ½»ö
            for (int d = 0; d < 4; d++) {
                int nx = i.n + dx[d];
                int ny = i.m + dy[d];

                if (nx >= N || ny >= M || nx < 0 || ny < 0
                        || visited[i.h][nx][ny] || map[i.h][nx][ny] != 0) continue;

                map[i.h][nx][ny] = cur+1;
                visited[i.h][nx][ny] = true;
                q.offer(new Info(i.h, nx, ny));
            }

            // À§¾Æ·¡Å½»ö
            for (int d = 0; d < 2; d++) {
                int nh = i.h + dh[d];

                if (nh >= H || nh < 0 || visited[nh][i.n][i.m] || map[nh][i.n][i.m] != 0) continue;

                map[nh][i.n][i.m] = cur+1;
                visited[nh][i.n][i.m] = true;
                q.offer(new Info(nh, i.n, i.m));
            }
        }
    }
}
