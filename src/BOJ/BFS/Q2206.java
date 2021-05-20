package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://ghkvud2.tistory.com/14
public class Q2206 {
    static int n, m, min = 987654321;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info {
        int x, y, wall, cnt;

        public Info(int x, int y, int wall, int cnt) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        bfs();

        if (min == 987654321) System.out.println(-1);
        else System.out.println(min);
    }

    private static void bfs() {
        Queue<Info> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2]; // 마지막 변수 : 벽을 부수고 온건지 안부수고 온건지

        q.offer(new Info(0, 0, 0, 1)); // 3번째 인자는 이미 부순적이 있는지 저장
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.cnt >= min) break;

            if (cur.x == n-1 && cur.y == m-1) {
                min = Math.min(min, cur.cnt);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                //  이전에 벽을 부수지 않은 경우
                if (cur.wall == 0) {
                    // 벽을 만났을 경우에는 이전에 벽을 부수지 않았으므로 해당 벽을 부수고 이동합니다. (wall을 1로 바꿈)
                    if (map[nx][ny] == 1) {
                        if (!visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            q.offer(new Info(nx, ny, 1, cur.cnt + 1));
                        }
                    }
                    else {
                        // 그냥 진행
                        if (!visited[nx][ny][0]) {
                            visited[nx][ny][0] = true;
                            q.offer(new Info(nx, ny, 0, cur.cnt + 1));
                        }
                    }
                }
                else { // 이전에 벽을 부순 경우
                        // 무조건 벽이 아니여야만 통과가능
                        if (!visited[nx][ny][1] && map[nx][ny] == 0) {
                            visited[nx][ny][1] = true;
                            q.offer(new Info(nx, ny, 1, cur.cnt + 1));
                        }

                }
            }
        }
    }
}
