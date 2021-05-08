package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14888 {

	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int TC;
	static int[] calc, arr;
	
	public static void calculate(int num, int idx) {
		if (idx == TC) {
			// num���� ������ ��������� �� ���� �׿��� ���� ����� ����Ǿ� ����!
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		for (int i = 0; i < 4; i++) {
			if (calc[i] > 0) {
				// ���� �ѹ� �Ҳ��ϱ� ����Ƚ�� ���ֱ�
				calc[i]--;
				switch(i) {
					case 0:	calculate(num + arr[idx], idx + 1);	break;
					case 1:	calculate(num - arr[idx], idx + 1);	break;
					case 2:	calculate(num * arr[idx], idx + 1);	break;
					case 3:	calculate(num / arr[idx], idx + 1);	break;
				}
				// �ٽ� ���󺹱ͽ��Ѿ� �ٸ� ����� ���� ���� �� �ִ�
				calc[i]++;
			}
			
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// input
		TC = Integer.parseInt(br.readLine());
		arr = new int[TC];
		calc = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < TC; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			calc[i] = Integer.parseInt(st.nextToken());
		
		calculate(arr[0], 1);
		System.out.println(max);
		System.out.println(min);
	}

}
