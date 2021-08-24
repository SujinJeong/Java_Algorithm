package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2583 {
    static int m, n, k;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static void fill(int startX, int startY, int endX, int endY) {

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (map[x][y] == 1) continue;
                // 방문처리
                map[x][y] = 1;
            }
        }
    }

    static int bfs(int x, int y) {
        Queue<Info> q = new LinkedList<>();

        // 하나의 영역 크기
        int cnt = 1;
        q.offer(new Info(x, y));
        map[x][y] = 1;
        while(!q.isEmpty()) {
            Info cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || nx >= m || ny < 0 || ny >=n) continue;
                if (map[nx][ny] == 1) continue;

                q.offer(new Info(nx, ny));
                map[nx][ny] = 1;
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        // 각 영역별 사이즈
        int[] size = new int[m*n];
        // 직사각형들 채우기
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            fill(startX, startY, endX, endY);
        }

        int idx = 0;
        // 영역나누기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    size[idx++] = bfs(i, j);
                }
            }
        }

        // 출력
        System.out.println(idx);
        Arrays.sort(size);
        for (int i : size) {
            if (i == 0) continue;
            System.out.print(i + " ");
        }
    }
}
