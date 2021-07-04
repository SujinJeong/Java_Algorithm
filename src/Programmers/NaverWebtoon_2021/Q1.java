package Programmers.NaverWebtoon_2021;

import java.util.Arrays;
/*
1. 금액, 할인율을 내림차순으로 정렬해서 큰금액 x 큰할인으로 정렬
2. 단순 계산 문제, double 처리하다가 삽질함
 */

public class Q1 {
    static class Solution {
        public int solution(int[] prices, int[] discounts) {
            int answer = 0;

            Arrays.sort(prices);
            Arrays.sort(discounts);

            int j = prices.length-1;
            for (int i = discounts.length-1; i >= 0; i--) {
                answer += prices[j] * (double)(100-discounts[i])/100;
                j--;
            }

            // 할인 못받는 경우
            for (int i = j; i >= 0; i--) {
                answer += prices[j];
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prices = {13000, 88000, 10000};
        int[] discounts = {30, 20};

        int ans = sol.solution(prices, discounts);
        System.out.println(ans);
    }
}
