package BOJ.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        while (n--> 0) {
            Stack<Character> st = new Stack<>();
            char[] c = br.readLine().toCharArray();

            for (int i = 0; i < c.length; i++) {
                if (!st.isEmpty() && st.peek() == c[i]) st.pop();
                else st.push(c[i]);
            }

            if (st.isEmpty()) cnt++;

        }
        System.out.println(cnt);
    }
}
