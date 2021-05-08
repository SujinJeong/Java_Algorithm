package BOJ.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11438 {
    static int n, m, k;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        k = getMaxDepth();

        depth = new int[n + 1];
        parent = new int[n + 1][k];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 1);
        fillParent();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int parent = lca(a, b);
            sb.append(parent + "\n");
        }

        System.out.println(sb);
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // depth ���߱�
        for (int i = k - 1; i >= 0; i--) {
            if ((1 << i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if (a == b) return a;

        // ����θ� ã�� ������ �ø���
        for (int i = k - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    private static int getMaxDepth() {
        int tmp = 1;
        int max = 0;
        while (tmp <= n) {
            tmp <<= 1;
            max++;
        }

        return max;
    }

    private static void fillParent() {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static void dfs(int cur, int cnt) {
        depth[cur] = cnt;

        for (int i = 0; i < tree[cur].size(); i++) {
            int x = tree[cur].get(i);
            if (depth[x] == 0) {
                dfs(x, cnt + 1);
                parent[x][0] = cur;
            }
        }
    }
}
