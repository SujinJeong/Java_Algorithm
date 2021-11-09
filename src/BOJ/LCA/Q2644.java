package BOJ.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2644 {
    static int[] parent;

    private static int getDepth(int a) {
        int cnt = 0;

        while (true) {
            a = parent[a];
            cnt++;
            if (a == 0) break;
        }

        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
        st = new StringTokenizer(br.readLine());
        int ans1 = Integer.parseInt(st.nextToken());
        int ans2 = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        int m = Integer.parseInt(br.readLine()); // 관계의 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            parent[c] = p;
        }

        int depth1 = getDepth(ans1);
        int depth2 = getDepth(ans2);


        // LCA
        // 먼저 높이 맞춰주기
        int totalCnt = 0;
        if (depth1 > depth2) {
            while (depth1-- != depth2) {
                ans1 = parent[ans1];
                totalCnt++;
            }
        }
        else if (depth1 < depth2) {
            while (depth1 != depth2--) {
                ans2 = parent[ans2];
                totalCnt++;
            }
        }

        boolean flag = true;
        // 하나씩 따라 올라가서 공통 조상 찾기
        while (ans1 != ans2) {
            ans1 = parent[ans1];
            ans2 = parent[ans2];

            // 최상단노드
            if (ans1 == 0 || ans2 == 0) {
                flag = false;
                break;
            }
            totalCnt += 2; // 양쪽이므로 2씩 더하기
        }

        if (flag)
            System.out.println(totalCnt);
        else
            System.out.println(-1);
    }
}
