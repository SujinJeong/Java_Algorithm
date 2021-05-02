package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n]; // 오름차순으로 정렬해서 길이 size-1 dp로 길이 구해주기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // solve
        dp[0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            // 들어오는 값이 더 크면 삽입
            if (arr[i] > dp[len-1]) {
                dp[len++] = arr[i];
            }
            else { // 들어오는 값이 더 작은 경우 이분탐색해서 해당 자리 replace
                int start = 0;
                int end = len;

                while (start <= end) {
                    int mid = (start+end)/2;

                    if (arr[i] > dp[mid])
                        start = mid +1;
                    else
                        end = mid -1;
                }
                dp[start] = arr[i];
            }
        }

        System.out.println(len);
    }
}
