package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14502 {
    static int n, m, max = Integer.MIN_VALUE;
    static int[][] map;
    static ArrayList<Info> virus;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void pickWall(int cnt) {
        if (cnt == 3) {
            int[][] newmap = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    newmap[i][j] = map[i][j];
                }
            }
            int[][] aftervirus = spreadVirus(newmap);
            max = Math.max(countArea(aftervirus), max);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    pickWall(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static int[][] spreadVirus(int[][] newmap) {

        for (int i = 0; i < virus.size(); i++) {
            Queue<Info> q = new LinkedList<>();
            q.offer(virus.get(i));

            while (!q.isEmpty()) {
                Info cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m
                            || newmap[nx][ny] == 1 || newmap[nx][ny] == 2) continue;

                    newmap[nx][ny] = 2;
                    q.offer(new Info(nx, ny));
                }
            }
        }
        return newmap;
    }

    public static int countArea(int[][] aftervirus) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (aftervirus[i][j] == 0) total++;
            }
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Info(i, j));
            }
        }
        pickWall(0);
        System.out.println(max);

    }
}
