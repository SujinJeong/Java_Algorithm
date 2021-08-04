package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q1038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 가장 작은 prefix부터 꺼내기 위한 pq
        PriorityQueue<Long> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        // 0~9일때
        if (n < 10) {
            System.out.println(n);
        }
        // n이 1022면 9876543210인데 0~9까지 다 썼으므로 더 이상 볼 필요 없음
        else if (n > 1022) {
            System.out.println(-1);
        }
        // 10~1022일때
        else {
            int cnt = 0;
            for (int i = 1; i <= 9; i++) {
                pq.add(new Long(i));
                cnt++;
            }

            while (cnt < n) {
                // 가장 작은 prefix 빼기
                long tmp = pq.poll();
                    for (int i = 0; i < tmp % 10; i++) {
                        pq.add(10 * tmp + i);
                        cnt++;
                        // System.out.print(tmp_num + " ");

                        // 몇번째 수 찾음
                        if (cnt == n) {
                            System.out.println(10 * tmp + i);
                            break;
                        }
                    }
            }
        }
    }
}
