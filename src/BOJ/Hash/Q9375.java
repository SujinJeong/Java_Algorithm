package BOJ.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        while (tc-->0) {
            HashMap<String, Integer> cloth = new HashMap<String, Integer>();
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                if (cloth.get(type) == null) cloth.put(type, 1);
                else cloth.put(type, cloth.get(type)+1);
            }

                int mul = 1;
                for (String key : cloth.keySet()) {
                    // 해당 종류를 입지 않는 경우의 수 1 포함
                    mul *= cloth.get(key)+1;
                }

            // 공집합 경우의 수 빼기
            sb.append((mul-1)+"\n");
        }
        System.out.print(sb);
    }
}
