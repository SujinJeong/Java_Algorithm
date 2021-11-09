package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16234 {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	static int n, min, max;
	
	public static class Info {
		
		public Info(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		int x, y;
		
		
	}
	public static boolean bfs(int x, int y) {
		Queue<Info> q = new LinkedList<>();
		ArrayList<Info> arr = new ArrayList<>(); // 값 변경
		boolean flag = false; // 인구 이동이 일어났는지 확인
		
		int total = map[x][y];
		int cnt = 1;
				
		q.offer(new Info(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			arr.add(new Info(cur.x, cur.y));
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
				
				int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);
				if (diff >= min && diff <= max) {
					visited[nx][ny] = true;
					q.offer(new Info(nx, ny));
					
					flag = true; // 인구이동 일어			
					// 합 계
					total += map[nx][ny];
					cnt++;
				}
			}
		}
		
		// 계산된 값으로 바꿔주기
		for (Info i : arr) {
			map[i.x][i.y] = total/cnt;
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		int totalCnt = 0;
		
		// input
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean isMoved = false;
			visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++ ) {
					if (!visited[i][j]) {
						if (bfs(i, j)) {
							isMoved = true; // 인구 이동 있었으
						}
					}	
				}
			}
			
			// 인구이동 없었으면 
			if (!isMoved) break;
			else totalCnt++;
		}
		
		System.out.println(totalCnt);

	}

}
