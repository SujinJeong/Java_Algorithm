package BOJ.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197 {
    static int[] parent;
    static int v, e;
    static class Info implements Comparable<Info> {
        int s, e, num;

        public Info(int s, int e, int num) {
            this.s = s;
            this.e = e;
            this.num = num;
        }

        @Override
        public int compareTo(Info o) {
            return this.num - o.num;
        }
    }

    public static void init() {
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

    }

    public static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static boolean union(int a, int b) {
        int p1 = findParent(a);
        int p2 = findParent(b);

        if (p1 != p2) {
            parent[p2] = p1;
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Info> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
//        ArrayList<Info>[] adj = new ArrayList[e+1];
//
//        for (int i = 1; i <=e; i++) {
//            adj[i] = new ArrayList<>();
//        }
        parent = new int[v+1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start= Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            int weight= Integer.parseInt(st.nextToken());
            pq.offer(new Info(start, end, weight));
        }

        init();
        int rslt = 0;
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (union(cur.s, cur.e)) {
                rslt += cur.num;
            }
        }

        System.out.println(rslt);
    }
}
