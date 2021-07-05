package BOJ.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 주차장 자리
        int[] parking_cost = new int[n];

        // 주차할 차량
        int[] car_cost = new int[m+1];

        // 지금 주차된 차량이 있는지
        boolean[] car = new boolean[n];

        for (int i = 0; i < n; i++) {
            parking_cost[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= m; i++) {
            car_cost[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> wait = new LinkedList<>();

        // 현재 차번호, 어느 자리 주차
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < m*2; i++) {
            q.add(Integer.parseInt(br.readLine()));
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            if (cur < 0) { // 주차 차량이 자리를 뺀 경우
                // 정산 후 빼주기
                int empty_num = hash.get(-cur);

                car[empty_num] = false;
                total += parking_cost[empty_num] * car_cost[-cur];
                
                // 차량이 빠지면서 자리가 생긴 경우 채워주기
                if (!wait.isEmpty()) {
                    int wait_car = wait.poll();
                    car[empty_num] = true;
                    hash.put(wait_car, empty_num);
                }
            }
            else { // 주차원한느 차량이 존재하는 경우
                boolean flag = false;

                for (int i = 0; i < car.length; i++) {
                    // 주차 자리 존재
                    if (car[i] == false) {
                        hash.put(cur, i);
                        car[i] = true;
                        flag = true;
                        break;
                    }
                }

                // 끝까지 탐색해도 주차 자리 존재하지 않으면 대기차량에 추가
                if (!flag) {
                    wait.add(cur);
                }
            }
        }

        System.out.println(total);
    }
}
