package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int ans = 987654321;
        int[][] dp = new int[n][3]; // k번째까지 해당 색깔으 선택했을 떄 cost 덧셈
        for (int s = 0; s < 3; s++) {

            for (int k = 0; k < 3; k++) {
                if (k == s) dp[0][k] = cost[0][k]; // 선택한 녀석만 초기화
                else dp[0][k] = 987654321; // 나머지 애들은 선택되지 않도록 최대값
            }

            for (int i = 1; i < n; i++) {
                // 겹치면 안되므로
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }


            for (int k = 0; k < 3; k++) {
                if (k == s) continue; // 선택된 녀석이 아닌 다른 애들만 min값 구해줘야하므로
                else ans = Math.min(dp[n-1][k], ans); // 만약 0이 선택됐으면 1, 2 선택한 경우만 갱신
            }

        }

        System.out.println(ans);
    }
}
