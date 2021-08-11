package BOJ.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while(tc--> 0) {
            int n =  Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int[] sort = new int[n];
            st = new StringTokenizer(br.readLine());
            // 입력
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            // 정렬
            Arrays.sort(arr);

            int start = 0;
            int end = n-1;
            // 좌, 우에 하나씩 차례로 넣어줘야 중간값이 가장 크고 원을 그렸을 떄 차이가 가장 적음
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) { // 짝수번째
                    sort[start++] = arr[i];
                }
                else { // 홀수번째
                    sort[end--] = arr[i];
                }
            }

            // 가장 작은 차이 구하기
            // 초기값은 가장 끝값 - 첫값
            int min = Math.abs(sort[n-1] - sort[0]);

            for (int i = 0; i < n-1; i++) {
                min = Math.max(Math.abs(sort[i]- sort[i+1]), min);
            }

            sb.append(min+"\n");
        }

        System.out.println(sb);
    }
}
