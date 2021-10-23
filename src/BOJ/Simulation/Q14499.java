package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14499 {

	// 동, 서, 남, 북
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dice;

	public static void changeDice(int dir) {
		int[] tmpDice = new int[6];

		// 원래 다이스 모양을 저장해놓을 배열
		for (int i = 0; i < 6; i++) {
			tmpDice[i] = dice[i];
		}

		switch (dir) {
		case 0: // 동
			dice[4] = tmpDice[0];
			dice[0] = tmpDice[5];
			dice[5] = tmpDice[2];
			dice[2] = tmpDice[4];
			break;
		case 1: // 서
			dice[5] = tmpDice[0];
			dice[0] = tmpDice[4];
			dice[4] = tmpDice[2];
			dice[2] = tmpDice[5];
			break;
		case 2: // 남
			dice[0] = tmpDice[3];
			dice[3] = tmpDice[2];
			dice[2] = tmpDice[1];
			dice[1] = tmpDice[0];
			break;
		case 3: // 북
			dice[3] = tmpDice[0];
			dice[0] = tmpDice[1];
			dice[1] = tmpDice[2];
			dice[2] = tmpDice[3];
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 1, 5, 6, 2, 3, 4
		// top = dice[0], botton = dice[2]
		dice = new int[6];
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int commandNum = Integer.parseInt(st.nextToken());

		// 지도 입력
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령 하나씩 처리
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < commandNum; i++) {
			int d = Integer.parseInt(st.nextToken()) - 1;

			int nx = sx + dx[d];
			int ny = sy + dy[d];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m)
				continue;

			// 범위 안에 존재하면 주사위 굴리기
			changeDice(d);
			
			if (map[nx][ny] == 0) { // 주사위 바닥면에 있는 수 -> 칸
				map[nx][ny] = dice[2];	
				
			} else { // 칸 -> 주사위 바닥면, 칸 = 0
				dice[2] = map[nx][ny];
				map[nx][ny] = 0;
			}
			
			sb.append(dice[0]+"\n");
			
			sx = nx;
			sy = ny;
		}

		System.out.println(sb);
	}

}
