package BOJ.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16202 {
    static int[] parent;

    public static class Info {
        int s,e, cost;

        public Info(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

    }

    public static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
    public static boolean union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa != pb) {
            parent[pa] = pb;
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Info[] arr = new Info[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Info(a, b, i+1);
        }

        for(int cnt = 0; cnt< k; cnt++) {

            int adj_cnt = 1;
            int adj_sum = 0;

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++)
                parent[i] = i;

            boolean flag = false;
            // 연결되지 않을 간선들빼고 돌면서 mst 만들어보기
            for (int j = cnt; j < m; j++) {
                // 모두 연결완료
                if (adj_cnt == n) {
                    // mst임
                    flag = true;
                }

                if (union(arr[j].s, arr[j].e)) { // 연결되있으면 계속 진행
                   adj_cnt++;
                   adj_sum += arr[j].cost;
                }
            }

            // mst가 안된 순간부터는 계속 안되므로
            if(!flag) {
                while (cnt++<k)
                    sb.append(0+" ");
                break;
            }

            sb.append(adj_sum+" ");
        }
        System.out.println(sb);
    }
}
