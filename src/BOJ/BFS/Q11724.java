package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q11724 {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            adj[to].add(from);
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < adj[cur].size(); i++) {
                int tmp = adj[cur].get(i);
                if (!visited[tmp]) {
                    q.offer(tmp);
                    visited[tmp] = true;
                }
            }
        }
    }
}
