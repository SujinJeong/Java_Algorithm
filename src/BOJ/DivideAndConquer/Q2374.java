package BOJ.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
/*
8번 틀렸는데 long 못잡아서 생긴 문제
–2,147,483,648 ~ 2,147,483,647
넘어보이면 long으로 선언하자 (10자리 넘으면)
 */
public class Q2374 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Long> dq = new ArrayDeque<>();
		long sum = 0;
		
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		for (int i = 0; i < n; i++) {
			if (dq.isEmpty()) dq.addFirst(arr[i]);
			else {
				// 오름차순일때
				while (dq.peekLast()< arr[i]) {
					long top = dq.pollLast();
					
					// 오름차순 중 최종 높이 맞춰주기
					if (dq.isEmpty() || dq.peekLast() > arr[i]) {
						sum += arr[i] - top;
						break;
					}
					else { // arr[i]보다 큰거 만날때까지(st.peek() > arr[i]) 한칸식 단계별로 맞춰주기
						sum += dq.peekLast() - top;
					}
				}
				// 높이 계산 후 본인 넣어주기
				dq.offerLast(arr[i]);
			}
			// System.out.println("sum: " + sum + ", stack 최상단: " + st.peek());
		}
		
		// 내림차순인 애들이 존재할때만 추가적으로 높이 맞춰주기
		if (dq.size() > 1) {
			sum += (dq.peekFirst() - dq.peekLast());
		}

		System.out.println(sum);
	}
}
