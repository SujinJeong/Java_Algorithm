package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16401 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i  = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr[arr.length-1];
        int max = Integer.MIN_VALUE;

        while (start <= end) {
            int mid = (start+end)/2;

            int cnt = 0;

            for (int i = 0; i < arr.length; i++) {
                cnt += arr[i] / mid;
            }

            if (cnt >= n) {
                start = mid + 1;
                // 조건을 만족하는 경우이므로 max 갱신
                max = Math.max(max, mid);
            }
            else {
                end = mid-1;
            }


        }
        if (max == Integer.MIN_VALUE) System.out.println(0);
        else System.out.println(max);
    }
}
