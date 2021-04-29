package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {
    public static class Info {
        int num, cnt;

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];
        Queue<Info> q = new LinkedList<>();
        visited[n] = true;
        q.offer(new Info(n, 0));

        while (!q.isEmpty()) {
            Info cur = q.poll();
            if (cur.num == k) {
                System.out.println(cur.cnt);
                break;
            }
            if (cur.num+1 <= 100000 && !visited[cur.num +1] ) {
                visited[cur.num +1] = true;
                q.offer(new Info(cur.num + 1, cur.cnt + 1));
            }
            if (cur.num-1 >= 0 && !visited[cur.num -1]) {
                visited[cur.num -1] = true;
                q.offer(new Info(cur.num - 1, cur.cnt + 1));
            }
            if (cur.num*2 <= 100000 &&!visited[cur.num*2] ) {
                visited[cur.num*2] = true;
                q.offer(new Info(cur.num * 2, cur.cnt + 1));
            }

        }
    }
}
