package Programmers.Lotte_2021;

import java.util.PriorityQueue;
/*
MST 문제
 */
public class Q2 {
    public static void main(String[] args) {

    }
    private static class Info implements Comparable<Info> {
        int start, end, cost;

        public Info(int s, int e, int c) {
            start = s;
            end = e;
            cost = c;
        }
        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }
    private static int[] p;
    private static void initParent(int V) {
        for(int i = 1; i <= V; i++) {
            p[i] = i;
        }
    }
    private static int findParent(int x) {
        if(p[x] == x) return x;
        else return p[x] = findParent(p[x]);
    }
    private static boolean unionFind(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a == b) return false;

        if(a < b) p[b] = a;
        else p[a] = b;

        return true;
    }
    private static int solution(int V, int[][] maps) {
        int cnt = maps.length;
        p = new int[V + 1];
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i = 0; i < cnt; i++) {
            pq.add(new Info(maps[i][0], maps[i][1], maps[i][2]));
        }

        initParent(V);

        int conn = 0, ans = 0;

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(unionFind(inf.start, inf.end)) {
                conn++;
                ans += inf.cost;
            }

            if(conn == V - 1) break;
        }

        return ans;
    }
}