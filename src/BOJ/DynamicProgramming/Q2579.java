package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // solve
        if (n >= 3) {
            // 3개를 한번에 뛰면 안돼므로 1. 2칸전 2. 3칸전+1칸 경우의 수뿐
            dp[0] = 0;
            dp[1] = arr[1];
            dp[2] = arr[1] + arr[2];
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i];
            }
            ans = dp[n];
        }
        else {
            while (n>0) {
                ans += arr[n];
                n--;
            }
        }
        System.out.println(ans);
    }
}
