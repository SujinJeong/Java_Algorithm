package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15663 {
    static int n, m;
    static ArrayList<Integer> num;
    static StringBuilder sb;
    public static void Perm(int cnt, int[] selected, boolean[] visited) {

        if (cnt == m) {
            for (int cur : selected) {
                sb.append(cur + " " );
            }
            sb.append("\n");
            return;
        }

        // cnt는 총 몇개 뽑았는지, i는 중복제거 하기 위해 시작 인덱스 조정
        ArrayList<Integer> select = new ArrayList<>();
        for (int i = 0; i < num.size(); i++) {
            if (!visited[i]) {
                int total = 0;
                for (int j = 0; j < select.size(); j++) {
                    if (num.get(i) == select.get(j)) total++;
                }

                if (total !=0) continue;
                visited[i] = true;
                selected[cnt] = num.get(i);
                select.add(num.get(i));
                Perm(cnt + 1, selected, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            num.add(tmp);
        }

        Collections.sort(num);
        Perm(0, new int[m], new boolean[n]);

        System.out.println(sb);
    }

}
