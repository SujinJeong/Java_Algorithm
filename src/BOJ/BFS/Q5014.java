package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5014 {
    static int[] dir = {1, -1};
    static int f, s, g, u, d;

    static class Info {
        int n, c;

        public Info(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }
    private static int bfs() {
        boolean[] visited = new boolean[f+1];
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(s, 0));
        visited[s] = true;

        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.n == g) {
                return cur.c;
            }

            for (int i = 0; i < 2; i++) {
                int nh = 0;

                if (i == 0) nh = cur.n + (dir[i]*u);
                else nh = cur.n + (dir[i]*d);

                if (nh < 1 || nh > f) continue;
                if (visited[nh]) continue;

                visited[nh] = true;
                q.offer(new Info(nh, cur.c +1));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken()); // 총 층수
        s = Integer.parseInt(st.nextToken()); // 강호가 지금 있는 곳
        g = Integer.parseInt(st.nextToken()); // 스타트링크 위치
        u = Integer.parseInt(st.nextToken()); // 한번에 위로 가는 층수
        d = Integer.parseInt(st.nextToken()); // 한번에 아래로 가는 층수

        int rslt = bfs();
        if (rslt != -1) System.out.println(rslt);
        else System.out.println("use the stairs");
    }
}
