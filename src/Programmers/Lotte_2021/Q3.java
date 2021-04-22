package Programmers.Lotte_2021;
/*
그리디 문제
 */
public class Q3 {

	public static void main(String[] args) {
		
		// a, b 동전 주머니 값은 input으로 주어짐
		int a = 0, b= 100;
		
		// a부터 시작
		int turn = 0;
		int cnt = 0;
		
		while(true) {
			
			int one_max = Math.max(a, b);
			int two_max = a > b ? b :a;
			
			if (one_max > two_max) {
				if (one_max - a == 0) {
					a -= one_max;
				}
				else {
					b -= one_max;
				}
			}
			else {
				a -= two_max;
				b -= two_max;
			}
			cnt++;
			
			if (a == 0 && b ==0 ) {
				break;
			}
			else {
				turn = (turn+1)%2;
			}
		}
		
		System.out.println(turn + " " + cnt);
	}

}
