package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q20056 {

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N, M, K;
	static ArrayList<Info>[][] arr; // 맵 해당위치에 저장되어 있는 파이어볼 정보
	static Queue<Info> fireball; // 명령 초기 상태에 이동 시켜야할 파이어볼
	// 아래 fireball 리스트를 안만들어주고 바로 맵에 추가하면 이미 추가한것 또 계속 이동시키는 오류 발생

	static class Info {
		int r, c, m, s, d;

		public Info(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}

		public Info(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	private static void step1() {
		while (!fireball.isEmpty()) {
			Info cur = fireball.poll();
			// 한칸씩 이동
			for (int i = 0; i < cur.s; ++i) {
				cur.r += dx[cur.d];
				cur.c += dy[cur.d];
				
				// 이동할때 범위 넘으면 다시 초기화
				if (cur.r > N)
					cur.r = 1;
				if (cur.c > N)
					cur.c = 1;
				if (cur.r < 1)
					cur.r = N;
				if (cur.c < 1)
					cur.c = N;
			}

			// 새로운 칸에 파이어볼 추가
			arr[cur.r][cur.c].add(cur);
		}
	}

	private static void step2() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j].size() == 1) {
					fireball.add(arr[i][j].get(0));
					arr[i][j].clear(); // 배열에 담아주고 해당자리 없애기
				} else if (arr[i][j].size() >= 2) {
					int sm = 0;
					int ss = 0;
					int even = 0;
					int odd = 0;
					for (Info cur : arr[i][j]) {
						sm += cur.m;
						ss += cur.s;

						if (cur.d % 2 == 0) {
							even++;
						} else {
							odd++;
						}

					}

					int size = arr[i][j].size();
					int em = sm / 5;
					int es = ss / size;

					// 필요한 계산 끝났으면 현재 존재하는 파이어볼 삭제 후 4개로 분리
					arr[i][j].clear();

					if (em == 0) { // 질량이 0이면 모두 사라짐
						continue;
					}
					// 모두 홀수이거나 짝수이면 방향 0, 2, 4, 6;
					if (even == size || odd == size) {
						fireball.add(new Info(i, j, em, es, 0));
						fireball.add(new Info(i, j, em, es, 2));
						fireball.add(new Info(i, j, em, es, 4));
						fireball.add(new Info(i, j, em, es, 6));
					} else {
						fireball.add(new Info(i, j, em, es, 1));
						fireball.add(new Info(i, j, em, es, 3));
						fireball.add(new Info(i, j, em, es, 5));
						fireball.add(new Info(i, j, em, es, 7));
					}
				}
			}
		}
	}

	private static int step3() {

		int total = 0;
		while(!fireball.isEmpty()) {
			total += fireball.poll().m;
		}

		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1][N + 1];
		fireball = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = new ArrayList<>();
			}
		}

		// 초기 입력값
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireball.add(new Info(r, c, m, s, d));
		}

		// k번만큼 반복
		while (K-- > 0) {

			step1(); // 파이어볼 이동
			step2(); // 2개 이상의 파이어볼 있는 칸 처리

		}

		System.out.println(step3()); // 남아있는 질량 합 구하기
	}

}
