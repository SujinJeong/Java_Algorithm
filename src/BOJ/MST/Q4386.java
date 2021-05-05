package BOJ.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q4386 {
    static Node[] map;
    static int[] parent;
    static int n;
    private static class Node {
        double x, y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Info implements Comparable<Info> {
        int idx1, idx2;
        double dist;

        public Info(int idx1, int idx2, double dist) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.dist = dist;
        }


        @Override
        public int compareTo(Info o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    private static void init() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static boolean union(int a, int b) {
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
        PriorityQueue<Info> pq = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());

        map = new Node[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            map[i] = new Node(x, y);

            for (int j = 0; j < i; j++) {
                double dist = Math.sqrt(Math.pow(map[j].x - x, 2) + Math.pow(map[j].y - y, 2));
                pq.add(new Info(i, j, dist));
            }
        }

        init();

        int cnt = 0;
        double ans = 0;
        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            if (cnt == n-1) break;
            if (union(cur.idx1, cur.idx2)) {
                ans += cur.dist;
                cnt++;
            }
        }

        System.out.println(String.format("%.2f", ans));

    }
}
