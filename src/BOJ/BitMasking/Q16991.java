package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16991 {
    static int n;
    static double[][] dist, dp;
    static int INF = 987654321;
    static Info[] node;

    private static class Info {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static double dfs(int node, int visit) {
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

    private static void initDp() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
    }

    private static double getDist(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    private static void initDist() {
        for (int i = 0; i < node.length; i++) {
            Info startNode = node[i];
            for (int j = 0; j < node.length; j++) {
                Info endNode = node[j];
                dist[i][j] = getDist(startNode.x, endNode.x, startNode.y, endNode.y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dist = new double[n][n];
        dp = new double[n][(1 << n) - 1];
        node = new Info[n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 좌표 받아오기
            node[i] = new Info(start-1, end-1);
        }

        // 거리갱신
        initDist();

        // dp배열 INF로 초기화
        initDp();

        // 어느 노드에서 출발하든 최소값은 같으므로 0부터 출발
        System.out.println(dfs(0, 1));
    }
}
