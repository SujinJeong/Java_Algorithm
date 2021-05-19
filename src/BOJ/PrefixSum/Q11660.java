package BOJ.PrefixSum;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
       int[][] arr = new int[n][n];
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 한줄씩 합구하기
        for (int i = 0; i < n; i++) {
            for (int j  = 1; j < n; j++) {
                arr[i][j] += arr[i][j-1];
            }
        }

        // 원하는 합 구하기
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            int sum = 0;
            for (int i = x1; i <= x2; i++) {
                if (y1 == 0) sum += arr[i][y2];
                else sum += arr[i][y2] - arr[i][y1-1];
            }
            sb.append(sum + "\n");
        }

        System.out.println(sb);
    }

}
