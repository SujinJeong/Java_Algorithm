package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q21610 {

	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int n, m;
	static int[][] map;
	static Command[] info;
	static Queue<Cloud> cloudInfo;
	
	static class Cloud {
		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		int x, y;
		
	}
	static class Command {
		int d, s;

		public Command(int d, int s) {
			super();
			this.d = d;
			this.s = s;
		}
	}
	
	static int copyMagic(int x, int y) {
		int cnt = 0;
		
		// 대각선만 확인
		for (int d = 1; d <= 7; d +=2) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			// 물이 있으면 카운트 증가
			if (map[nx][ny] > 0) cnt++;
		}
		
		return cnt;
	}
	static void solve() {
		cloudInfo = new LinkedList<>();
		
		// 첫 구름 위치
		cloudInfo.add(new Cloud(n-1, 0));
		cloudInfo.add(new Cloud(n-1, 1));
		cloudInfo.add(new Cloud(n-2, 0));
		cloudInfo.add(new Cloud(n-2, 1));
		
		// 명령동안 반복
		for (int i = 0; i < info.length; i++) {
			// 3번에서 사라지는 구름 위치 기록
			boolean[][] visited = new boolean[n][n];
			
			int size = cloudInfo.size(); // 기존 구름 개수 카운트
			// 1 : 정해진 방향으로 모든 구름 이동
			for(int s = 0; s < size; s++) {
				Cloud cur = cloudInfo.poll();
				
				// 다음 이동해야할 거리
				// 순환이기 때문에 나머지 연산 필요함
				int nextX = (cur.x + dx[info[i].d] * info[i].s) % n;
				int nextY = (cur.y + dy[info[i].d] * info[i].s) % n;
				
				// 유효범위 벗어날때 확인
				if (nextX < 0) {
					nextX += n;
				}
				if (nextY < 0) {
					nextY += n;
				}
				
				// 구름 이동된 위치 다시 삽입
				cloudInfo.add(new Cloud(nextX, nextY));
			}
			
			// 2 : 구름 있는 칸이 비가 1씩 내리고 구름 사라짐
			while(!cloudInfo.isEmpty()) {
				Cloud cur = cloudInfo.poll();
				map[cur.x][cur.y]++; // 비내림
				visited[cur.x][cur.y] = true; // 사라진 구름 위치 기록
				
			}
			
			// 3 : 물복사마법 
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (visited[j][k]) {
						// 물복사마법까지 모두 덧셈
						map[j][k] += copyMagic(j, k);
					}
				}
			}
			
			// 4 : 구름 있었던 칸 제외 물의 양이 2 이상이면 물 -2 구름 생김
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (!visited[j][k] && map[j][k] >= 2) {
						cloudInfo.add(new Cloud(j, k));
						map[j][k] -= 2;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		info = new Command[m];
		
		// input
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 이동정보
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			info[i] = new Command(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		// 단계별 로직
		solve();
		
		int answer = 0;
		// 최종 합 확인
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
	}

}
