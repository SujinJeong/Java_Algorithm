package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17070 {

    // 그래프 ver 이 이해하기 더 쉽지만, dp버전이 시간더 빠름
    // dp[x][y][현재파이프모양] 형식 (0: 가로, 1: 세로, 2: 대각선)
    // 만약, 현재 경우가 세로라면 그 전에 올 수 있는 경우의 수는 1: 세로, 2: 대각선임을 이용
    static int total;
    static int[][] dp;
    static int n;

        public static boolean checkValid(int x, int y ) {
        if (x < 0 || x >= n || y < 0 || y >= n) return false;
        if (dp[x][y] == 1) return false;

        return true;
    }
    public static void moving(int x, int y, int dir) {

            // 목표지점에 도달할때마다 방법의 수 갱신
            if (x == n-1 && y == n-1) {
                if (checkValid(n-1, n-1)) total++;
            }

            // 가로
            if (dir == 0) {
                // 가로
                if (checkValid(x, y+1)) moving(x, y+1, 0);
                // 대각선
                if (checkValid(x+1, y+1) && checkValid(x+1, y) && checkValid(x, y+1)) moving(x+1, y+1, 2);
            }
            // 세로
            else if (dir == 1) {
                // 세로
                if (checkValid(x+1, y)) moving(x+1, y, 1);
                // 대각선
                if (checkValid(x+1, y+1) && checkValid(x+1, y) && checkValid(x, y+1)) moving(x+1, y+1, 2);
            }
            // 대각선
            else {
                // 가로
                if (checkValid(x, y+1)) moving(x, y+1, 0);
                // 세로
                if (checkValid(x+1, y)) moving(x+1, y, 1);
                // 대각선
                if (checkValid(x+1, y+1) && checkValid(x+1, y) && checkValid(x, y+1)) moving(x+1, y+1, 2);
            }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // dir 0: 가로 / 1: 세로 / 2 :대각선
        // 초기값은 0,1에 가로
        moving(0, 1, 0);

        System.out.println(total);
    }
}

// dp ver. 공통 부분은 없앰.
//        처음에는 0행, 1열에 가로모양 파이프의 끝이 놓여져있다
//        dp[0][1][0] = 1;
//
//                //그러므로 0행 2열부터 파이프를 놓을 수 있기에 반복문 아래와 같이 수행
//                for(int i = 0; i < n; i++) {
//        for(int j = 2; j < n; j++) {
//        //놓으려는 위치에 벽이 있다면 스킵
//        if(map[i][j] == 1) continue;
//        //i번쨰 행, j번째 열에 가로로 파이프가 놓여지기 위해서는
//        //i번쨰 행, j-1번째 열에 가로, 대각으로 파이프가 놓여있을 수 있는 경우 두개를 더해주어야함
//        dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
//
//        //세로, 대각의 경우 i가 일떄 이전 dp값을 불러올 수 없으므로 스킵
//        if(i == 0) continue;
//        //i번쨰 행, j번째 열에 세로로 파피프가 놓여지기 위해서는
//        //i - 1번쨰 행, j번쨰 열에 세로, 대각으로 파이프가 놓여있을 수 있는 경우 두개를 더해주어야함
//        dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
//
//        //대각의 경우 놓는 위치 , 놓는 위치의 위, 놓는 위치의 왼쪽이 비어있어야함
//        if(map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
//        //대각은 이전 가로, 세로, 대각으로부터 놓아질 수 있으므로 다 더해줌
//        dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
//        }
//        }
//        //최종적으로 n-1 행 열에 놓여진 0, 1, 2 모양의 모든 파이프 경우의 수를 더해주면 된다
//        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);