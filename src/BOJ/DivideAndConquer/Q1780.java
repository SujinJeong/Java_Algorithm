package BOJ.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    static int[][] map;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        ans = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, n);

        System.out.println(ans[0]);
        System.out.println(ans[1]);
        System.out.println(ans[2]);
    }

    private static void solve(int x, int y, int s) {

        int tmp = map[x][y];
        boolean flag = true;

        for (int i = x; i < x+s; i++) {
            for (int j = y; j < y+s; j++) {
                if (map[i][j] != tmp) flag = false;
            }
        }

        if (flag) {
            ans[tmp+1]++;
            return;
        }

        int size = s/3;
        solve(x, y, size);
        solve(x+size, y, size);
        solve(x+size*2, y, size);
        solve(x, y+size, size);
        solve(x, y+size*2, size);
        solve(x+size, y+size, size);
        solve(x+size*2, y+size, size);
        solve(x+size, y+size*2, size);
        solve(x+size*2, y+size*2, size);
    }
}
