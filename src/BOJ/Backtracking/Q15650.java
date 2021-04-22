package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 : 조합
 */
public class Q15650 {
	static int n, m;
	static int[] num;
	
	public static void Comb(int start, int cnt, int[] selected) {
		
		if (cnt == m) {
			for (int cur : selected)
				System.out.print(cur + " ");
			System.out.println();
			return;
		}
		
		// cnt는 총 몇개 뽑았는지, i는 중복제거 하기 위해 시작 인덱스 조정
		for (int i = start; i < num.length; i++) {
			selected[cnt] = num[i];
			Comb(i+1, cnt+1, selected);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[n];
		for (int i = 1; i <= n; i++) {
			num[i-1] = i;
		}
		Comb(0, 0, new int[m]);
	}

}
