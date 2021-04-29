package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q16198 {
    static ArrayList<Integer> arr;
    static int max = Integer.MIN_VALUE;

        private static void dfs(ArrayList<Integer> arr, int sum) {
            if(arr.size()<=2) {
                max = Math.max(max, sum);
                return;
            }

            for(int i=1; i<arr.size()-1; i++) {
                int temp = arr.get(i);
                int num = arr.get(i-1) * arr.get(i+1);
                arr.remove(i);
                dfs(arr, sum + num);
                arr.add(i, temp); // 되돌려놓기
            }
        }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        dfs(arr, 0);
        System.out.println(max);
    }
}
