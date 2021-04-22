package Programmers.Lotte_2021;

public class Q1 {
/*
반절복사, 반전 비트반전
n =1 0
n =2 01
n =3 0110
n =4 01101001
 */
	public static void main(String[] args) {
	}
		// 예제는n=3 0-> n=4 가는 기준
		public String solution(int n, long x, long y) {
			long bit = 0;
			for (int i = 0; i < n-1; i++) {
				// 총길이구하기
				long len = (long)Math.pow(2, i);
				// 10000-1 = 1111
				long compare = (1<<len)-1;
				// 1111 ^ 0110 = 1001
				long tmp = bit ^ compare;
				// 뒤에 1001이 들어가기위해 자리 만들어주기 0110xxxx
				bit = bit << len;
				// 두개붙이기
				bit += tmp;
			}
			
			long len = (long) Math.pow(2, n - 1);
			StringBuilder res = new StringBuilder();
			for (long i = x; i <= y; ++i) {
				if ((bit & (1 << len - i)) == 0) {
					res.append("0");
				} else {
					res.append("1");
				}
			}
			return res.toString();
		}
	}

