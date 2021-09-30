package Programmers.BFSDFS;

public class 타겟넘버 {
    static class Solution {

        static int total_cnt;

        static void dfs(int[] numbers, int index, int sum, int target) { //몇번째인지, 현재까지 합

            // 마지막 인덱스가 되었을때
            if (index == numbers.length) {
                if (sum == target) { // 타켓에 도달한 경우
                    total_cnt++;
                }
                // 타켓에 도달하지 못한 경우 카운트 더하지 않고 그냥 return
                return;
            }
            // 마지막 인덱스가 되기 전까지 작업
            else {
                // + 하는 경우
                dfs(numbers, index + 1, sum + numbers[index], target);
                // - 하는 경우
                dfs(numbers, index + 1, sum - numbers[index], target);
            }

        }

        public int solution(int[] numbers, int target) {
            int answer = 0;

            // 하나씩 탐색
            dfs(numbers, 0, 0, target);
            answer = total_cnt;
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {1, 1, 1, 1, 1};
        int ans = sol.solution(numbers, 3);
        System.out.println(ans);
    }
}
