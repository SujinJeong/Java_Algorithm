package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q16922 {
    static int n;
    static ArrayList<Integer> sum;
    static int[] num = {1, 5, 10, 50};

    public static void Comb(int start, int cnt, int[] selected) {
        if (cnt == n) {
            int total = 0;
            for (int i = 0; i < selected.length; i++) {
                total += selected[i];
            }
            if (!sum.contains(total)) sum.add(total);
            return;
        }

        for (int i = start; i < num.length; i++) {
            selected[cnt] = num[i];
            Comb(i, cnt+1, selected);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        sum = new ArrayList<>();

        Comb(0, 0, new int[n]);
        System.out.println(sum.size());

    }
}
