package Programmers.NaverWebtoon_2021;

//Logic
//KMP 알고리즘을 더이상 교환 불가능할때까지 돌리는 방식으로 해결
//StringBuilder를 이용해서 문자열 조작의 시간초과나 메모리 초과를 방지하였다

//public class Solution3 {
//    private static class Solution {
//        private int solution(String s, String t) {
//            int answer = 0;
//            int pat_length = t.length();
//            StringBuilder parent = new StringBuilder(s);
//            char[] pattern = t.toCharArray();
//            int[] table = new int[pattern.length];
//
//            //pattern 이용하여 pi테이블 생성
//            makeTable(pattern, table);
//
//            //KMP 반복
//            while(true) {
//                //-1을 리턴하면 더이상 일치하는 문자열 없기에 종료
//                int ret = KMP(parent, pattern, table);
//
//                if(ret == -1) break;
//                else {
//                    //일치하는 문자열 존재하면 해당 부분 delete해주고 변환 횟수++
//                    parent.delete(ret - pat_length + 1, ret + 1);
//                    answer++;
//                }
//            }
//            return answer;
//        }
//        private int KMP(StringBuilder parent, char[] pattern, int[] table) {
//            int parentSize = parent.length(), patternSize = pattern.length;
//            int j = 0;
//
//            for(int i = 0; i < parentSize; i++) {
//                while(j > 0 && pattern[j] != parent.charAt(i)) {
//                    j = table[j - 1];
//                }
//                if(pattern[j] == parent.charAt(i)) {
//                    if(j == patternSize - 1) {
//                        //KMP알고리즘을 통해서 패턴의 끝에 도달 즉 일치하는게 존재하는 순가
//                        //원본 문자열의 index를 리턴
//                        return i;
//                    } else {
//                        j++;
//                    }
//                }
//            }
//            //일치하는게 없으면 -1 리턴
//            return -1;
//        }
//        private void makeTable(char[] pattern, int[] table) {
//            int patternSize = pattern.length, j = 0;
//
//            for (int i = 1; i < patternSize; i++) {
//                while (j > 0 && pattern[j] != pattern[i]) {
//                    j = table[j - 1];
//                }
//                if(pattern[j] == pattern[i]) {
//                    table[i] = ++j;
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Solution sol = new Solution();
//        System.out.println(sol.solution("abc", "ab"));
//    }
//}

public class Q3 {
    public static class Solution {
        public int solution(String s, String t) {
            int result = 0;

            // 패턴에 대한 테이블이기 때문에 한번만 수행해도 무방
            int[] table = makeArr(t);

            String parent = s;
            // 또 찾아보기
            while (true) {
                parent = kmp(parent, t, table);
                if (parent.equals("*")) break;
                result++;
            }

            return result;
        }
    }

    public static int[] makeArr(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(i)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(i)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static String kmp(String parent, String pattern, int[] pi) {

        StringBuilder sb = new StringBuilder();
        sb.append(parent);

        int j = 0; int cnt = 0;
        for (int i = 0; i < parent.length(); i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                // 틀린경우에는 그 전 인덱스까지 접두사/접미사가 같은 부분까지 계속 이동
                j = pi[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                // pattern 끝까지 탐색 완료되었을 경우
                if (j == pattern.length()-1) {
                    // 늘어난 i에서 patternsize만큼 빼주기

                    int start = i-pattern.length()+1;
                    sb.replace(start, start+pattern.length(),"");
                    parent = sb.toString();

                    return parent;
                }
                // pattern 끝까지 더 탐색
                else j++;
            }
        }

        return "*";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aabcbcd";
        String t = "abc";

        int ans = sol.solution(s, t);
        System.out.println(ans);

    }
}
