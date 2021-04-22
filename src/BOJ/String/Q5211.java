package BOJ.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Q5211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Character> ga = new HashSet<>();
        HashSet<Character> da = new HashSet<>();


        ga.add('A');
        ga.add('D');
        ga.add('E');

        da.add('C');
        da.add('F');
        da.add('G');

        String[] s = br.readLine().split("\\|");
        int g1_cnt = 0;
        int d1_cnt = 0;

        for (int i = 0; i < s.length; i++) {
            if (ga.contains(s[i].charAt(0))) {
                g1_cnt++;
            }
            else if (da.contains(s[i].charAt(0))) {
                d1_cnt++;
            }
        }

        String rslt = "";

        if (g1_cnt < d1_cnt) rslt = "C-major";
        else if (g1_cnt > d1_cnt) rslt = "A-minor";
        else { // 같은 경우
            String tmp = s[s.length-1];
            char last = tmp.charAt(tmp.length()-1);

            if (ga.contains(last)) rslt = "A-minor";
            else rslt = "C-major";
        }

        System.out.println(rslt);
    }
}
