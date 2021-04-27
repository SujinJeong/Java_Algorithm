package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
Ǯ�̽ð�: 2�ð�
������ �ε��� ���� �ʿ�!!!!!!!!!
 */
public class Q17144 {
    static int r, c, t;
    static int[][] map;
    static Queue<Info> q;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class Info {
        int x, y, num;

        public Info(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        q = new LinkedList<Info>();
        ArrayList<Info> air = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    air.add(new Info(i, j, map[i][j]));
                }
            }
        }

        while (t--> 0) {
            // �̼����� ã��
            findDust();
            // �̼����� ����
            bfs();
            // ����û��
            clear(air);

//            for (int i = 0; i < r; i++) {
//                for (int j = 0; j < c; j++)
//                    System.out.print(map[i][j] + " ");
//                System.out.println();
//            }
        }

        // ���� �̼����� ���
        System.out.println(calc());

    }

    private static int calc() {
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) cnt += map[i][j];
            }
        }

        return cnt;
    }

    private static void findDust() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0 ) {
                    q.add(new Info(i, j, map[i][j]));
                }
            }
        }
    }
    // ���� ��ǥ-air �ݽð� ����, �Ʒ� ��ǥ-air.x+1 �ð����
    private static void clear(ArrayList<Info> air) {
        Info up = air.get(0);
        // System.out.println(air.get(0).x + " " + air.get(1).y);
        Info down = air.get(1);
        int rmax = r-1;
        int cmax = c-1;
        
        // �ݽð����
        // ���� �� ���� �ε��� ���� ���� ����
        int tmp = map[0][up.y];
        // ��
        for (int i = up.y; i < cmax; i++) {
            map[0][i] = map[0][i+1];
        }
        // ��
        for (int i = 0; i < up.x; i++) {
            map[i][cmax] = map[i+1][cmax];
        }
        // ��
        for (int i = cmax; i > up.y; i--) {
            if (map[up.x][i-1] == -1) {
                map[up.x][i] = 0;
                continue;
            }
            map[up.x][i] = map[up.x][i-1];
        }
        // ��
        for (int i = up.x; i > 0; i--) {
            if (map[i][0] == -1) {
                continue;
            }
            map[i][up.y] = map[i-1][up.y];
        }
        map[1][up.y] = tmp;

        // �ð����
        tmp = map[rmax][cmax];
        // ��
        for (int i = rmax; i > down.x; i--) {
            map[i][cmax] = map[i-1][cmax];
        }
        // ��
        for (int i = cmax; i > down.y; i--) {
            if (map[down.x][i-1] == -1) {
                map[down.x][i] = 0;
                continue;
            }
            map[down.x][i] = map[down.x][i-1];
        }
        // ��
        for (int i = down.x; i < rmax; i++) {
            if (map[i][down.y] == -1) {
                continue;
            }
            map[i][down.y] = map[i+1][down.y];
        }
        // ��
        for (int i = 0; i < cmax; i++) {
            map[rmax][i] = map[rmax][i+1];
        }
        map[rmax][cmax-1] = tmp;
    }
    private static void bfs() {

        while (!q.isEmpty()) {
            Info cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x+dx[d];
                int ny = cur.y+dy[d];
                int nw = cur.num / 5;

                if (nx >= r || ny >= c || nx < 0 || ny < 0) continue;
                if (map[nx][ny] == -1) continue;

                map[nx][ny] += nw;
                map[cur.x][cur.y] -= nw;
            }
        }
    }
}
