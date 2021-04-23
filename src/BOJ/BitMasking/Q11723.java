package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] arr = new boolean[21];
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int i  =0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String console = st.nextToken();

            if (console.equals("all")) {
                    Arrays.fill(arr, true);
            } else if (console.equals("empty")) {
                    Arrays.fill(arr, false);
            } else {
                int num = Integer.parseInt(st.nextToken());

                if (console.equals("add")) {
                    arr[num] = arr[num] | true;
                } else if (console.equals("remove")) {
                    arr[num] = arr[num] & false;
                } else if (console.equals("check")) {
                    if(arr[num]) sb.append("1\n");
                    else sb.append("0\n");
                } else if (console.equals("toggle")) {
                    arr[num] = arr[num] ^ true;
                }
            }
        }

        System.out.println(sb);
    }
}
