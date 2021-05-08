package BOJ.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
   1. �ش� ���� ����� ���� �ʰ�, �� �������� �Ѿ�� ���. => �� dp���� �ش� ���� dp���� �ȴ�
   2. �ش� ���� ����� �ϰ�, �ش� ����� �ϴµ� �ɸ��� �� �ϼ� ��ŭ �Ѿ�� ��� => 1�� ���ؼ� max ���� dp���� �־��ش�
 */
public class Q14501 {
	static int[] days, money, dp;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// input
		int TC = Integer.parseInt(br.readLine());
		days = new int[TC+1];
		money = new int[TC+1];
		// ������ �ε��� �ڿ� ����� 0���� ������� ����
		dp = new int[TC+2];
		
		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		// sol
		for (int i = TC; i >= 1; i--) {
			// ��� �ñ⸦ ������ �� ū ������ �� �� ���� ������ �� ���Ͱ� ��������
			if (i + days[i] > TC+1)
				dp[i] = dp[i+1];
			// �ں��� ���ʴ�� ����ؿ� ������ = dp[i+1]�� 
			// �̸� ������ �ɸ��� �ϼ��� �� ũ���� ������ Ŭ �� dp[i+days[i]] = �ɸ��� �ϼ� + money[i] = ����
			// �� ������ �����ϴ� ���� ������ �����ϱ�
			else {
				dp[i] = Math.max(dp[i+1], dp[i+days[i]]+ money[i]);
			}
		}
		
		// output
		System.out.println(dp[1]);
	}

}
