package Programmers.Kakao_2021_Intern;

import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        });
    }
    private static int[] solution(String[][] places) {
        int[] answer = new int[5];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for(int room = 0; room < 5; room++) { //각 대기실에 대해서 반복문
            answer[room] = 1; //일단 해당 방의 거리두기가 되어있다고 가정
            loop:for(int i = 0; i < 5; i++) { //자리가 놓여져있는 방향대로 2중 for문
                for(int j = 0; j < 5; j++) {
                    if(places[room][i].charAt(j) == 'P') { 
                        Queue<int[]> q = new LinkedList<>();
                        boolean[][] visited = new boolean[5][5];
                        q.add(new int[]{i, j, 0});
                        visited[i][j] = true;

                        while(!q.isEmpty()) {
                            int[] cur = q.poll();

                            for(int dir = 0; dir < 4; dir++) {
                                int nx = cur[0] + dx[dir];
                                int ny = cur[1] + dy[dir];

                                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                                if(places[room][nx].charAt(ny) == 'X') continue;

                                if(!visited[nx][ny]) {
                                    visited[nx][ny] = true;

                                    if(places[room][nx].charAt(ny) == 'P' && cur[2] < 2) {
                                        answer[room] = 0;
                                        break loop;
                                    }
                                    q.add(new int[]{nx, ny, cur[2] + 1});
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}