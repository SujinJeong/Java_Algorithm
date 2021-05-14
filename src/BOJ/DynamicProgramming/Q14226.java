package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q14226 {
    static int S;
    static boolean[][] visited;

    static class Info {
        int s, c, cnt;

        public Info(int s, int c, int cnt) {
            this.s = s;
            this.c = c;
            this.cnt = cnt;
        }
    }
    private static void bfs() {
        Queue<Info> q = new LinkedList<>();
        visited[1][0] = true; // 해당 케이스를 방문한 적 있는지
        q.offer(new Info(1, 0, 0));

        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.s == S) {
                System.out.println(cur.cnt);
                break;
            }

            // 1번
            // 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기
            if (!visited[cur.s][cur.s]) {
                visited[cur.s][cur.s] = true;
                q.offer(new Info(cur.s, cur.s, cur.cnt + 1));
            }

            // 2번, 3번 조건은 같은 화면이지만 다른 클립보드 상태를 가질 수 있으므로 visited 필요
            // 2번
            // 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없으며
            if (cur.c > 0 && cur.s + cur.c <= 1000 && !visited[cur.s + cur.c][cur.c]) {
                visited[cur.s + cur.c][cur.c] = true;
                q.offer(new Info(cur.s + cur.c, cur.c, cur.cnt+1));
            }
            // 3번
            // 하나 뺐을 때 음이면 안된다
            if (cur.s-1 >= 0 && !visited[cur.s-1][cur.c]) {
                visited[cur.s-1][cur.c] = true;
                q.offer(new Info(cur.s-1, cur.c, cur.cnt+1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        visited = new boolean[1001][1001]; // 첫번째는 화면 이모티콘, 두번째는 클립보드

        bfs();
    }
}
