package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2210 {
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static String[][] arr;
    static int total;
    static HashSet<String> hash = new HashSet<>();
    public static void dfs(int x, int y, int depth, String rslt) {

        if (depth == 6) {

            // 없는 수 일경우
            if (!hash.contains(rslt)) {
                hash.add(rslt);
                total++;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >=5) continue;
            dfs(nx, ny, depth+1, rslt+arr[nx][ny]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new String[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 1, arr[i][j]);
            }
        }

        System.out.println(total);
    }
}
