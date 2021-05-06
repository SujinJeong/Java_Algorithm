package Programmers.Kakao_2019_Intern;

import java.util.Stack;

public class Crain {
    static class Solution {
        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < moves.length; i++) {
                int pick = moves[i]-1;

                // 최상단 끄집어서 바구니에 넣기
                for (int j = 0; j < board.length; j++) {
                    if (board[j][pick] != 0) {
                        if (!st.isEmpty() && st.peek() == board[j][pick]) {
                            st.pop();
                            board[j][pick] = 0;
                            answer += 2;
                        }
                        else {
                            st.push(board[j][pick]);
                            board[j][pick] = 0;
                        }
                        break;
                    }
                }
                
            }
            return answer;
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{0,0,0,0,0}, {0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(sol.solution(board, moves));
    }
}
