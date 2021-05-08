package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2529 {
    static int tc;
    static char[] c;
    static boolean[] visited;
    static ArrayList<String> ans;

    private static void solve(int num, int cnt, String s) {

        if (cnt == tc) { // �ε�ȣ �� ä������
            ans.add(s);
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                if (c[cnt] == '<') {
                    if (num >= i) continue;
                } else if (c[cnt] == '>') {
                    if (num <= i) continue;
                }
                visited[i] = true;
                solve(i, cnt + 1, s + i);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tc = Integer.parseInt(br.readLine());
        c = new char[tc];
        visited = new boolean[10];
        ans = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tc; i++) {
            c[i] = st.nextToken().charAt(0);
        }

        // ù��°����
        for (int i = 0; i < 10; i++) {
            visited[i] = true;
            solve(i, 0, i+"");
            visited[i] = false;
        }

        System.out.println(ans.get(ans.size()-1) + "\n" + ans.get(0));
    }
}
