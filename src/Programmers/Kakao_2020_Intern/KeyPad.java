package Programmers.Kakao_2020_Intern;

public class KeyPad {
    static class Solution {
        static class Info {
            int x, y;

            public Info(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int getDist(int x1, int x2, int y1, int y2) {
            return Math.abs(x1-x2) + Math.abs(y1-y2);
        }

        public String solution(int[] numbers, String hand) {
            String answer = "";

            int[][] map = new int[4][3];
            Info left = new Info(3, 0);
            Info right = new Info(3, 2);

            for (int i = 0; i < numbers.length; i++) {
                int tmp = numbers[i];

                if (tmp == 1 || tmp == 4 || tmp == 7) {
                    left = new Info(tmp/3, 0);
                    answer += "L";
                }
                else if (tmp == 3 || tmp == 6 || tmp == 9) {
                    right = new Info((tmp/3)-1, 2);
                    answer += "R";
                }
                else {
                    int nx = 0;
                    if (tmp == 0) nx = 3;
                    else nx = tmp/3;

                    int ld = getDist(left.x, nx, left.y, 1);
                    int rd = getDist(right.x, nx, right.y, 1);

                    if (ld < rd) {
                        left = new Info(nx, 1);
                        answer += "L";
                    }
                    else if (ld == rd) {
                        if (hand.equals("right")) {
                            right = new Info(nx, 1);
                            answer += "R";
                        }
                        else {
                            left = new Info(nx, 1);
                            answer += "L";
                        }
                    }
                    else {
                        right = new Info(nx, 1);
                        answer += "R";
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";
        System.out.println(sol.solution(numbers, hand));
    }
}
