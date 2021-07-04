package Programmers.NaverWebtoon_2021;

import java.util.ArrayList;

public class Q2 {

    static class Solution {
        public String[] solution(String s) {
            String[] answer = {};
            // 자른 문자열 저장을 위한 배열
            ArrayList<String> cnt = new ArrayList<>();

            String new_s = s;
            // 접두, 접미 같은 길이가 1이상
            while (true) {
                int[] pi = makeArr(new_s.toCharArray());
                int length = pi[pi.length-1];

                // 1. 문자는 남았지만 더이상 겹치는 것 없음
                if (length == 0) {
                    cnt.add(new_s);
                    // 데칼코마니가 안되는 독립된 문자열이 있으므로 cnt.size()-2부터
                    for (int i = cnt.size()-2; i >= 0; i--) {
                        cnt.add(cnt.get(i));
                    }
                    break;

                } // 2. 모두 데칼코마니가 될 경우 -> 겹치는거 처리끝
                else if (new_s.length() / 2 == length) {
                    for (int i = cnt.size()-1; i >= 0; i--) {
                        cnt.add(cnt.get(i));
                    }
                    break;
                }

                // 일단 현재 상태에서 겹치는 부분 담고
                cnt.add(new_s.substring(0, length));
                // 앞, 뒤 자른 새로운 문자열 생성
                new_s = new_s.substring(length, new_s.length() - length);

            }

            // 원본 배열에 copy
            answer = new String[cnt.size()];
            int idx = 0;
            for(String tmp : cnt) {
                answer[idx++] = tmp;
            }

            cnt.toArray(new String[cnt.size()]);

            return answer;
        }
    }

    public static int[] makeArr(char[] p) {
        int[] table = new int[p.length];
        int j = 0;

        for (int i = 1; i < p.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = table[j - 1];
            }
            if (p[i] == p[j]) {
                table[i] = ++j;
            }
        }

        return table;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcxyasdfasdfxyabc";

        String[] ans = sol.solution(s);
        for (String st : ans)
            System.out.println(st + " ");
    }

}
