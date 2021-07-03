package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // dp 1과 2의 초기값
        int a = 1; int b = 2;
        int[] dp = new int[n+1];

        if (n >= 3) {
            dp[1] = a;
            dp[2] = b;

            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
            }

            System.out.println(dp[n]);
        }
        else if (n == 2) {
            System.out.println(b);
        }
        else System.out.println(a);
    }
}
