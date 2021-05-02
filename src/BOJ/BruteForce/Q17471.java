package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17471 {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int n, total;
    static int[] weight; // 가중치 담은 배열
    static int min = 987654321;

    public static boolean bfs() {
        Queue<Integer> q = new LinkedList<>();

        int start = 0;
        boolean[] cp = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            cp[i] = visited[i];
            if (!cp[i]) start = i;
        }

        q.offer(start);
        cp[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < adj[cur].size(); i++) {
                int tmp = adj[cur].get(i);
                if (!cp[tmp]) {
                    cp[tmp] = true;
                    q.offer(tmp);
                }
            }
        }
        // 연결안된 지역이 하나라도 있으면
        for (int i = 1; i <= n; i++)
            if (!cp[i]) return false;

        return true;
    }

    public static void dfs(int num, int depth, int sum) {
        // 2선거구 sum
        int two_sum = total - sum;
        // 1선거구 sum
        int one_sum = sum;

        if (depth == n || min == 0) {
            return;
        }
        // 똑같은 경우의 수 두번 세는것 방지
        if (one_sum > two_sum) return;

        visited[num] = true;
        // 작을 때만 계산해줘야 시간 줄일 수 있음
        if (two_sum-one_sum < min && bfs()) {
            // 1,2 지역구 차이로 min 갱신
            min = Math.min(two_sum-one_sum, min);
        }

        for (int i =1; i <= n; i++) {
            if (visited[i]) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int cur = adj[i].get(j);
                    if (!visited[cur])
                        dfs(cur, depth + 1, sum + weight[cur]);
                }
            }
        }
// 이렇게쓰면 5->6 / 5->7 연결되어있는 경우 5->7이 누락된다 dfs+bfs 혼합 필요
//        for (int i = 0; i < adj[num].size(); i++) {
//            int cur = adj[num].get(i);
//            if (!visited[cur]) {
//                dfs(cur, depth + 1, sum + weight[cur]);
//            }
//        }
        visited[num] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        weight = new int[n+1]; // weight 담은 배열
        visited = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            total += weight[i];
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            for (int j = 0; j < time; j++) {
                int num = Integer.parseInt(st.nextToken());
                adj[i].add(num);
            }
        } // end of input

        for (int i = 1; i <= n; i++) {
            dfs(i, 1, weight[i]);
        }

        if (min == 987654321) System.out.println(-1);
        else System.out.println(min);
    }
}
