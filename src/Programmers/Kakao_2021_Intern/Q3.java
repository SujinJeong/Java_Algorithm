package Programmers.Kakao_2021_Intern;


import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q3 {

    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            String answer = "";
            StringTokenizer st;
            int[] flag = new int[n];
            // 초기설정
            for (int i = 0; i < n; i++) {
                flag[i] = 1;
            }

            // 현재 선택된
            int selected = k;
            // 마지막 삭제
            //Stack<Integer> last_deleted = new Stack<>();
            int[] last_deleted = new int[k+1];
            int last_deleted_idx = 0;
            // 움직여야하는 인덱스
            int move_idx = 0;

            for (int i = 0; i < cmd.length; i++) {
                st = new StringTokenizer(cmd[i]);
                String alpha = st.nextToken();

                // up이나 down
                if (st.hasMoreTokens()) {
                    move_idx = Integer.parseInt(st.nextToken());
                }

                int tmp = selected;
                int cnt = 0;
                switch(alpha) {
                    case "U": // 현재 선택된 행에서 X칸 위에 있는 행을 선택

                        while (true) {
                            if (flag[tmp] == 1 && cnt == move_idx) {// 삭제된행이면 건너뛰기
                                break;
                            }
                            tmp--;
                            // 없는 행이면 cnt로 치면안된다
                            if (flag[tmp] == 0) continue;
                            cnt++;
                        }
                        selected = tmp;
                        break;
                    case "D": // 현재 선택된 행에서 X칸 아래에 있는 행을 선택
                        while (true) {
                            if (flag[tmp] == 1 && cnt == move_idx) {// 삭제된행이면 건너뛰기
                                break;
                            }
                            tmp++;
                            // 없는 행이면 cnt로 치면안된다
                            if (flag[tmp] == 0) continue;
                            cnt++;
                        }
                        selected = tmp;
                        break;
                    case "C": // 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                        flag[selected] = 0;
                        last_deleted[last_deleted_idx++] = selected;
                        boolean isAllDeleted = false;

                        if (selected < n-1) {
                            while (true) {
                                tmp++;
                                if (flag[tmp] == 1) {
                                    break;
                                }
                                if (tmp == n-1 && flag[tmp] == 0) { // 밑에가 모두 삭제된 경우
                                    isAllDeleted = true;
                                    break;
                                }
                            }
                            selected = tmp;
                        }

                        // 1. 삭제된 행이 가장 마지막 행이거나 2. 밑에가 모두 삭제된 경우 위로 추적
                        if (isAllDeleted || selected == n-1) {
                            while (true) {
                                tmp--;
                                if (flag[tmp] == 1) break;
                            }
                            selected = tmp;
                        }
                        break;
                    case "Z":
                        int last = last_deleted[last_deleted_idx-1];
                        flag[last] = 1;
                        // 삭제
                        last_deleted_idx--;
                        break;
                } // end of switch
            } // end of cmd

            StringBuilder sb = new StringBuilder();
            for (int i : flag) {
                if (i == 1) sb.append("O");
                else sb.append("X");
            }
            answer = sb.toString();
            return answer;
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(sol.solution(n, k, cmd));
    }
}
