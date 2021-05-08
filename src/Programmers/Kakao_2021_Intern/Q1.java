package Programmers.Kakao_2021_Intern;


import java.util.HashMap;

public class Q1 {
    static class Solution {
        public int solution(String s) {
            int answer = 0;
            String rslt  ="";
            HashMap<String, Integer> hash = new HashMap<>();
            hash.put("zero", 0);
            hash.put("one", 1);
            hash.put("two", 2);
            hash.put("three", 3);
            hash.put("four", 4);
            hash.put("five", 5);
            hash.put("six", 6);
            hash.put("seven", 7);
            hash.put("eight", 8);
            hash.put("nine", 9);
            char[] c = s.toCharArray();
            //String rslt = s.replaceAll("[0-9]","");
            int idx = 0;
            loop : while (true) {
                if (idx == c.length) break;

                String tmp = "";
                while (Character.isAlphabetic(c[idx])) {
                    tmp += c[idx++];
                    if (hash.get(tmp) != null) {
                        rslt += hash.get(tmp);
                        System.out.println(tmp);
                        tmp ="";
                    }
                    if (idx == c.length) break loop;
                }
                // 숫자일때
                rslt += c[idx++];
            }

            answer = Integer.parseInt(rslt);
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "123";
        System.out.println(sol.solution(s));
    }
}
