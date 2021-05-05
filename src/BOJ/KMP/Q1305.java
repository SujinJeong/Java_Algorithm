package BOJ.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1305 {

    static int[] pi;
    private static void makeTable(char[] pattern) {
        int j = 0;
        pi = new int[pattern.length];
        for (int i = 1; i< pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j-1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        makeTable(s);

        System.out.println(n-pi[n-1]);
    }
}
