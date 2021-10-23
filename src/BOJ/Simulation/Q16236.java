package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1. 상어가 먹을 수 있는 물고기가 있는지 확인
2. 먹을 수 있는 물고기들의 정보를 저장
3. 거리가 가장 가까운 물고기를 먹으러 갑니다
-1순위: 거리가 제일 가까운것
-2순위: 가장 위에 있는거
-3순위: 그중 가장 왼쪽에 있는거

4. 먹을 수 있는 물고기가 없을때까지 반복
 */
public class Q16236 {
	static Pos shark;
	static PriorityQueue<Pos> fish;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int n, cnt, time, size = 2;
	// cnt : 먹은 물고기 수, time : 총 걸린시간, size: 상어 크

	public static class Pos implements Comparable<Pos> {
		int x, y, dist;

		public Pos(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.dist == o.dist) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return this.dist - o.dist;
			}
		}

	}

	public static void solve() {

		while (true) {
			// 상어위치부터 탐색
			if (bfs(shark.x, shark.y)) {

				// 현재 먹을 물고
				Pos cur = fish.poll();

				// 물고기 먹기
				cnt++;
				time += cur.dist;
				map[cur.x][cur.y] = 0; // 먹은 물고기 빈칸으로

				if (cnt == size) { // 자신의 크기와 같은 수의 물고기 먹을때마다 크기 증가
					size++;
					cnt = 0;
				}

				
				shark = new Pos(cur.x, cur.y, 0); // 상어 위치 재설정

			}
			else break;

		}
	}

	private static boolean bfs(int x, int y) {
		PriorityQueue<Pos> q = new PriorityQueue<>(); // 상어 이동경로 기억
		fish = new PriorityQueue<>(); // 이동할때마다 유효한 물고기를 담기 위한 배
		boolean flag = false; // 먹을 물고기가 있는
		boolean[][] visited = new boolean[n][n];

		// 처음 상어 위치부터 출발
		q.offer(shark);
		visited[shark.x][shark.y] = true;

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
					continue;
				
				// 사이즈가 같거나 0이면 지나갈 수만 있음
				if (size == map[nx][ny] || map[nx][ny] == 0) {
					// 상어 위치 갱신
					q.offer(new Pos(nx, ny, cur.dist + 1));
					visited[nx][ny] = true;
				}
				
				// 먹을 수 있는 물고기 자
				if (1 <= map[nx][ny] && map[nx][ny] < size) {
					// 유효한 물고기 위치 담기
					fish.add(new Pos(nx, ny, cur.dist + 1));
					// 상어 이동
					q.offer(new Pos(nx, ny, cur.dist + 1));
					
					visited[nx][ny] = true;
					flag = true;
				}

			}
		}

		return flag;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Pos(i, j, 0);
					map[i][j] = 0;
				}
			}
		}

		solve();

		System.out.println(time);
	}

}
