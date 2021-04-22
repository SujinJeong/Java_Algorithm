package Programmers.Kakao_2021;

public class Q2 {

	static class Solution {
		static int[] selected;
		static int col_max = Integer.MIN_VALUE;
		static int total_max = Integer.MIN_VALUE;

		public static int check(int[][] needs) {

			int total_cnt = 0;
			// for문 돌면서 0, 1 확인
			for (int i = 0; i < needs.length; i++) {
				boolean i_flag = true;
				for (int j = 0; j < needs[i].length; j++) {

					// 필요한 모든 물품에 대해 검사
					if (needs[i][j] == 1) {
						boolean j_flag = false;
						// 로봇이 처리할 수 있는지 확인
						for (int k = 0; k < selected.length; k++) {
							// 로봇이 처리가능
							if (selected[k] == j)
								j_flag = true;
						}

						// 해당 index 로봇이 처리 못하면 탈출
						if (!j_flag) {
							i_flag = false;
							break;
						}
					}

				}
				
				if (i_flag) total_cnt++;
			}
			
			return total_cnt;
		}

		private static void comb(int[][] needs, int cnt, int start, int r) {
			if (cnt == r) {
				total_max = Math.max(check(needs), total_max);
				return;
			}
			for (int i = start; i < col_max; ++i) {
				selected[cnt] = i;
				comb(needs, cnt + 1, i + 1, r);
			}
		}

		public int solution(int[][] needs, int r) {
			// needs 세로길이 - 만들 수 있는 완제품 개수
			// needs 세로인덱스 - 완제품 번호
			// needs 가로인덱스 - 부품 번호
			// needs[x][y] == 1 이면 y 부품이 필요하다는 의미
			// if y 길이 넘어가는 부품 번호는 다 필요없음 즉, 0
			int answer = 0;

			for (int i = 0; i < needs.length; i++)
				col_max = Math.max(needs[i].length, col_max);

			// 어떤 로봇 선택할건지 조합 실행
			selected = new int[r];
			comb(needs, 0, 0, r);
			
			answer = total_max;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] needs = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		int r = 2;
		System.out.println(sol.solution(needs, r));
	}
}
