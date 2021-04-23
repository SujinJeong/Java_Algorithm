package BOJ.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());

            if (tmp == 0) {
                if (pq.isEmpty()) sb.append(0 + "\n");
                else sb.append(pq.poll()+"\n");
            }
            else { // 숫자 들어온 경우
                pq.add(tmp);
            }
        }

        System.out.println(sb);
    }
}
