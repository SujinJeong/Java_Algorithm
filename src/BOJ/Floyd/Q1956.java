package BOJ.Floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1956 {

    static int INF = 987654321;
    static int v, e;
    static int min = INF;
    static int[][] adj;

    private static void floyd() {
        for (int i = 1; i <= v; i++) {
            for (int k = 1; k <= v; k++) {
                for (int j = 1; j <= v; j++) {
                    if (i == j) continue;
                    if (adj[i][j] > adj[i][k] + adj[k][j]) {
                        adj[i][j]= adj[i][k] + adj[k][j];
                    }
                }
            }
        }
    }


    private static void checkCycle() {



        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (adj[i][j] != INF && adj[j][i] != INF)
                    min = Math.min(min, (adj[i][j] + adj[j][i]));
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new int[v+1][v+1];

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                adj[i][j] = INF;
            }
        }

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[start][end] = weight;
        }

        floyd();
        checkCycle();

        if (min == INF) System.out.println(-1);
        else System.out.println(min);
    }

}
