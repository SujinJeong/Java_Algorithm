package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14728 {
    public static class Info {
        int weight, profit;

        public Info(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Info[] arr = new Info[n+1];
        int[][] dp = new int[n+1][k+1]; // 행: 물품번호 열: 배낭 무게 -> 값은 1~k까지 최대 이익 저장
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i] = new Info(w, p);
        }

        // solve
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (arr[i].weight > j) { // 무게가 이미 크면 전에 값 가져오기
                    dp[i][j] = dp[i-1][j];
                }
                else { // 배낭에 더 들어갈 수 있으므로 합해본 값과 이전 값 비교
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i].weight] +arr[i].profit);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }
}
