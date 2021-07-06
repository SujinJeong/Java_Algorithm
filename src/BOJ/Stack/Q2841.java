package BOJ.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2841 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int total = 0;

        ArrayList<Integer>[] num = new ArrayList[n+1];

        for (int i = 1; i <= n; i++)
            num[i] = new ArrayList();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cn = Integer.parseInt(st.nextToken());
            int cp = Integer.parseInt(st.nextToken());

            num[cn].add(cp);
        }

        for (int i = 1; i <= n; i++) {
            // 아무것도 없는 경우
            if (num[i].isEmpty()) continue;

            // 프렛존재
            Stack<Integer> s = new Stack<>();

            for (int j = 0; j < num[i].size(); j++) {
                int cur = num[i].get(j);

                // 비어있는 스택이거나 현재값이 더 높을때
                if (s.isEmpty() || cur > s.peek()) {
                    s.push(cur);
                    total++;
                }

                else if (cur < s.peek()) {
                    // 현재 값이 더 클때까지 뺴기
                    while (true) {
                        s.pop();
                        total++;
                        if (s.isEmpty() || cur > s.peek()) {
                            // 다른 숫자인 경우 해당 숫자 누르고 넣어주기
                            s.push(cur);
                            total++;
                            break;
                        }
                        // 맨 위 숫자가 현재 숫자랑 같으면 다시 누를 필요없음
                        if (cur == s.peek()) break;
                    }
                }
            }
        }

        System.out.println(total);
    }
}
