package BOJ.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> cnt = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        while (n-- > 0) {
            String name = br.readLine();
            if (cnt.get(name) == null) cnt.put(name, 1);
            else cnt.put(name, cnt.get(name) + 1);
        }

        while (m-- > 0) {
            String name = br.readLine();
            if (cnt.get(name) == null) cnt.put(name, 1);
            else cnt.put(name, cnt.get(name) + 1);
        }

        int rslt = 0;
        for (String key : cnt.keySet()) {
            if (cnt.get(key) >= 2) {
                rslt++;
                arr.add(key);
            }
        }
        Collections.sort(arr);

        Collections.sort(arr);
        sb.append(rslt+"\n");
        for (String s : arr)
            sb.append(s+"\n");
        System.out.print(sb);
    }
}
