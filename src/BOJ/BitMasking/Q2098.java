package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2098 {
    static int n;
    static int[][] dist, dp;
    static int INF = 987654321;

    private static int dfs(int node, int visit) {

        // base-condition
        // 모두 방문했을 경우 다시 0번 노드로 돌아가는 값 계산
        if (visit == ((1 << n) - 1)) {
            // 연결되지 않아있으면 사이클(TSP)가 성립될 수 없는 경우
            if (dist[node][0] == 0 ) return INF;
            // 다시 0으로 가는 길이 연결되어있으면
            return dist[node][0];
        }

        // 갱신된적 있으면 다시 들어가지 않음
        if (dp[node][visit] != INF)
            return dp[node][visit];

        for (int next = 0; next < n; next++) {
            // 다음노드와 연결되어있고 방문하지 않은 경우에만 계산
            if (dist[node][next] != 0 && (visit & (1 << next)) == 0) {
                // 최소 갱신, 방문처리
                dp[node][visit] = Math.min(dp[node][visit], dfs(next, visit | (1 << next)) + dist[node][next]);
            }
        }

        return dp[node][visit];
    }

    private static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dist = new int[n][n];
        dp = new int[n][(1 << n) - 1];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp배열 INF로 초기화
        init();

        // 어느 노드에서 출발하든 최소값은 같으므로 0부터 출발
        System.out.println(dfs(0, 1));
    }
}
