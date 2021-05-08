package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1���� N���� �ڿ��� �߿��� �ߺ� ���� M���� �� ����, ���� �� ������ ���� : �ߺ�����
-> �������� visited ����

 */
public class Q15654 {
    static int n, m;
    static int[] num;
    static StringBuilder sb;

    public static void Perm(int cnt, int[] selected, boolean[] visited) {

        if (cnt == m) {

            for (int cur : selected)
                System.out.print(cur + " ");
            System.out.println();
            return;
        }

        // cnt�� �� � �̾Ҵ���, i�� �ߺ����� �ϱ� ���� ���� �ε��� ����
        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[cnt] = num[i];
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

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i - 1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        Perm(0, new int[m], new boolean[n]);

        System.out.println(sb);
    }

}
