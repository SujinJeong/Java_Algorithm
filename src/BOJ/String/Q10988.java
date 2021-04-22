package BOJ.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int flag = 1;
        for (int i = 0, j = s.length()-1; i < s.length();i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                flag = 0;
                break;
            }
        }

        System.out.println(flag);
    }
}
