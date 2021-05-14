package BOJ.BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16197 {
    static char[][] map;
    static int n, m ,INF =987654321;
    static ArrayList<Info> coin;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Count {
        Info info;
        int cnt;

        public Count(Info info, int cnt) {
            this.info = info;
            this.cnt = cnt;
        }
    }

    public static int bfs(int i) {
        Queue<Count> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new Count(coin.get(i), 0));
        visited[coin.get(i).x][coin.get(i).y] = true;

        while (!q.isEmpty()) {
            Count coin = q.poll();

            if (coin.cnt >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                int nx = coin.info.x + dx[d];
                int ny = coin.info.y + dy[d];

                // 벽이면 skip
                if (map[nx][ny] == '#') continue;

                // 벗어나면 횟수 return
                if (nx < 0 || nx >= n || ny< 0 || ny >= m) {
                    return coin.cnt + 1;
                }

                q.offer(new Count(new Info(nx, ny), coin.cnt+1));
                visited[nx][ny] = true;
            }
        }
        return INF;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        coin = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') coin.add(new Info(i, j));
            }
        }

        int min = INF;
        // 동전 두개다
        min = Math.min(bfs(0), bfs(1));
        if (min == INF || min == -1) System.out.println(-1);
        else System.out.println(min);
    }
}
