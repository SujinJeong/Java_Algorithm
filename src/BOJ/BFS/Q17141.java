package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17141 {
    static int n, m, total_min = 987654321;
    static int[][] map;
    static Info[] sel;
    static ArrayList<Info> virus;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info {
        int x, y, time;

        public Info(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int[][] spreadVirus(int[][] newmap, Info[] selected) {

        Queue<Info> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < selected.length; i++) {
            q.offer(selected[i]);
            visited[selected[i].x][selected[i].y] = true;
        }

            while (!q.isEmpty()) {
                Info cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                    // 벽인경우
                    if (newmap[nx][ny] == -1) continue;
                    
                    newmap[nx][ny] = cur.time;
                    visited[nx][ny] = true;
                    q.offer(new Info(nx, ny, cur.time+1));
                }
            }

        return newmap;
    }

    public static int getMax(int[][] aftervirus) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (max < aftervirus[i][j])
                    max = aftervirus[i][j];
            }
        }
        return max;
    }
    private static void dfs(int cnt, int idx, Info[] sel, boolean[] isSelected) {
        if(cnt == m) {
            int[][] newmap = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newmap[i][j] = map[i][j];
                }
            }


            for (int i= 0; i < virus.size(); i++)
                // 선택되지 않은 virus들 0으로
                if (!isSelected[i])
                    newmap[virus.get(i).x][virus.get(i).y] = 0;
                // 선택된 애들 -1로
                else
                    newmap[virus.get(i).x][virus.get(i).y] = -2;

            int[][] aftervirus = spreadVirus(newmap, sel);

//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(aftervirus[i][j] + " ");
//                }
//                System.out.println();
//            }

            if (isAllfilled(aftervirus))
                total_min = Math.min(getMax(aftervirus), total_min);
            return;
        }

        for(int i = idx; i < virus.size(); i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                sel[cnt] = virus.get(i);
                dfs(cnt + 1, i, sel, isSelected);
                isSelected[i] = false;
            }
        }
    }

    private static boolean isAllfilled(int[][] aftervirus) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (aftervirus[i][j] == 0) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Info(i, j, 1));
                if (map[i][j] == 1) map[i][j] = -1;

            }
        }
        dfs(0, 0, new Info[m], new boolean[virus.size()]);
        if (total_min == 987654321) System.out.println(-1);
        else System.out.println(total_min);
    }
}