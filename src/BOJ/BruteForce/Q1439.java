package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c = br.readLine().toCharArray();
        int[] cnt = new int[2];

        for(int i = 1; i < c.length; i++) {
            if (c[i-1] != c[i]) {
                cnt[c[i-1] - '0']++;
            }
        }

        System.out.println(cnt[0] > cnt[1]? cnt[0]: cnt[1]);
    }


}
