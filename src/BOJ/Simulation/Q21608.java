package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q21608 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static ArrayList<Integer>[] info;
    static int[][] map;
    static int n;

    static class Student implements Comparable<Student> {
        int x, y, cnt, empty;

        public Student(int x, int y, int cnt, int empty) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.empty = empty;
        }

        @Override
        public int compareTo(Student s1) {
            if(this.cnt == s1.cnt) {
                if(this.empty == s1.empty) {
                    if(this.x == s1.x) {
                        return this.y - s1.y;
                    } else {
                        return this.x - s1.x;
                    }
                } else {
                    return s1.empty - this.empty;
                }
            } else {
                return s1.cnt - this.cnt;
            }
        }
    }

    public static Student bfs(int turn, int x, int y) {
        int stu_cnt = 0;
        int empty_cnt = 0;

        // 사방탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= n || nx < 0 || ny >= n || ny < 0) continue;
            if (map[nx][ny] == 0) {
                empty_cnt++;
            } else {
                // 유효성 범위 안이면
                for (int i = 0; i < 4; i++) {
                    int cur = info[turn].get(i);

                    // 좋아하는 친구 주변에 있으면
                    if (map[nx][ny] == cur) {
                        stu_cnt++;
                        break;
                    }
                }
            }
        }

        return new Student(x, y, stu_cnt, empty_cnt);
    }

    public static int cntFav(int turn, int x, int y) {
        int cnt = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            for (int i = 0; i < 4; i++) {
                if (map[nx][ny] == info[turn].get(i)) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        // 좋아하는 사람 정보 배열
        info = new ArrayList[n*n+1];
        for (int i = 1; i <= n*n; i++) {
            info[i] = new ArrayList<>();
        }
        // 정사각 배열
        map = new int[n][n];

        // input
        for (int i = 0; i < n*n; i++) {
            PriorityQueue<Student> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                info[num].add(Integer.parseInt(st.nextToken()));
            }

            // sol
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (map[x][y] == 0) { // 빈칸이면 탐색
                       pq.add(bfs(num, x, y));
                    }
                }
            }

            Student priorStd = pq.poll();
            map[priorStd.x][priorStd.y] = num;
        }

        int answer = 0;
        // 만족도 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int totalCnt = cntFav(map[i][j], i, j);
                switch (totalCnt) {
                    case 1:
                        answer += 1;
                        break;
                    case 2:
                        answer += 10;
                        break;
                    case 3:
                        answer += 100;
                        break;
                    case 4:
                        answer += 1000;
                        break;
                }
            }
        }

        System.out.println(answer);
    }
}
