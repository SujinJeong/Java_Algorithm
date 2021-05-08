package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2961 {
	static int[] arr; // ���� ���� �ε��� ����, visited�� ����� ����
	static int[] S, B;
	static int N;
	static int min = Integer.MAX_VALUE;
	
	static void Comb(int cnt, int idx, int r) {
	    if (cnt == r) {
	    	int sum = 0; int multi = 1;
	    	for (int i = 0 ; i < arr.length; i++) {
	    		// ���� ���� �ε������� ��� arr �̿��� ����, ����
	    		multi *= S[arr[i]];
	    		sum += B[arr[i]];
	    	}
	    	// �Ÿ��� ������ �ּҰ� ���ϱ�
	    	min = Math.min(min, Math.abs(multi-sum));
	    	return;
	    }
	    
	    for (int i = idx; i < N; i++) {
	    	arr[cnt] = i;
	    	Comb(cnt+1, i+1, r);
	    }
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N]; // �Ÿ�
		B = new int[N]; // ����
		
		// input
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int r = 1; r <= N; r++) { // nyr���� r��
			arr = new int[r]; // �̴� ����ŭ �ε��� ����ֱ� ���� �迭����
			Comb(0, 0, r); // Comb�Լ��� r����ŭ �̰�;��
		}
		System.out.println(min);
	}

}
