package BOJ.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2170 {

    static class Info implements Comparable<Info> {
        int s, e;

        public Info(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Info o) {
            return this.s - o.s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Info[] arr = new Info[n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Info(start, end);
        }

        // 시작점을 알아야하기 때문에 시작점 기준 오름차순 정렬
        Arrays.sort(arr);

        // 현재 구간 나타내는 인덱스
        int start = -1000000001;
        int end = - 1000000001;
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            Info cur = arr[i];
            if (end < cur.s) { // 포함할 수 없는 경우
                // 이전값계산
                ans += end-start;

                // 새로운 구간 생성
                start = cur.s;
                end = cur.e;
            }
            else { // 포함되는경우 end와 cur.e 비교해서 더 큰값으로 늘리기
                end = Math.max(end, cur.e);
            }
        }

        // 마지막 구간도 더해주기
        ans += end-start;

        System.out.println(ans);
    }
}
