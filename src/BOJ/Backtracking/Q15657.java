package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15657 {
    static int n, m;
    static int[] num;
    static StringBuilder sb;

    public static void Comb(int start, int cnt, int[] selected) {

        if (cnt == m) {
            for (int cur : selected)
                sb.append(cur + " ");
            sb.append("\n");
            return;
        }

        // cnt�� �� � �̾Ҵ���, i�� �ߺ����� �ϱ� ���� ���� �ε��� ����
        for (int i = start; i < num.length; i++) {
            selected[cnt] = num[i];
            Comb(i, cnt+1, selected);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i - 1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        Comb(0, 0, new int[m]);

        System.out.println(sb);
    }

}
