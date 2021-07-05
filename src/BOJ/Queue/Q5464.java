package BOJ.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 순서 대기
        Queue<Integer> q = new LinkedList<>();
        // 대기차량
        Queue<Integer> wait = new LinkedList<>();
        PriorityQueue<Integer> empty_parking = new PriorityQueue<>();
        // 현재 차번호, 어느 자리 주차
        HashMap<Integer, Integer> hash = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 주차장 자리
        int[] parking_cost = new int[n];

        // 주차할 차량
        int[] car_cost = new int[m+1];

        for (int i = 0; i < n; i++) {
            parking_cost[i] = Integer.parseInt(br.readLine());
            empty_parking.add(i);
        }

        for (int i = 1; i <= m; i++) {
            car_cost[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        for (int i = 0; i < m*2; i++) {
            q.add(Integer.parseInt(br.readLine()));
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            
            if (cur < 0) { // 주차 차량이 자리를 뺀 경우
                // 주차장 빈자리에 추가 후 total 합산
                int empty_num = hash.get(-cur);
                empty_parking.add(empty_num);
                total += parking_cost[empty_num] * car_cost[-cur];

                // 차량이 빠지면서 자리가 생긴 경우 채워주기
                if (!wait.isEmpty()) {
                    int wait_car = wait.poll();
                    hash.put(wait_car, empty_parking.poll());
                }
            }
            else { // 주차원하는 차량이 존재하는 경우
                // 주차할 수 있는 자리가 있는지 찾아보기
                if (!empty_parking.isEmpty()) hash.put(cur, empty_parking.poll());

                // 주차 자리 존재하지 않으면 대기차량에 추가
                else wait.add(cur);
            }
        }

        System.out.println(total);
    }
}
