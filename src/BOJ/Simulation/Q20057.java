package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20057 {
	static int n;
	static int[][] map;
	static int[] dx = {0,1,0,-1};   //토네이토의 x 이동 방향
	static int[] dy = {-1,0,1,0};   //토네이토의 y 이동 방향
	// 맨 오른쪽 그리고 위부터 1~9
	static int[] sandPercentage = {1, 1, 2, 7, 7, 2, 10, 10, 5};
	// 각 방향에서 (0~4) y기준(즉, 옮긴 위치)로 봤을때 1~9의 위치
	static int[][] sandMoveX = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},    //모래가 퍼지는 x방향
            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
	static int[][] sandMoveY = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},    //모래가 퍼지는 y방향
            {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
	// 왼쪽 아래 오른쪽 위가 한 사이클 (사이클마다 더해지고 빼지는 값)
	static int[] moveCnt = {1, 1, 2, 2};
	
	// 화살표 끝으로 움직이고 -> 흩뿌리고 -> 현재위치 변경
	private static int tornado() {
		
		int currentX = n/2;
		int currentY = n/2;
		int totalOut = 0; // 구해야 하는 값
		
		loop: while(true) {
			// 왼쪽 아래 오른쪽 위가 한 사이클
			for (int d = 0; d< 4; d++) {
				// 총 몇번 이동해야 하는지
				for (int cnt = 0; cnt < moveCnt[d]; cnt++) {
					// 1 :  화살표 끝으로 움직이기
					int nx = currentX + dx[d];
					int ny = currentY + dy[d];
					
					// 1,1 까지 모두 돈 경우
					if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
						break loop;
					}
					
					// 2 : 흩뿌리기
					int totalSand = 0;
					for (int i = 0; i < 9; i++) {
						int sandAmount = map[nx][ny] * sandPercentage[i] / 100;
						// 덧셈할 좌표
						int sumX = nx + sandMoveX[d][i];
						int sumY = ny + sandMoveY[d][i];
						
						totalSand += sandAmount; // 알파 값 구해주기 위해
						
						// 범위 벗어나는 경우 모래의 양 합산
						if (sumX < 0 || sumX >= n || sumY < 0 || sumY >= n) {
							totalOut += sandAmount;
						}
						// 모래가 있는 칸으로 이동하면 모래의 양은 더해짐
						else {
							map[sumX][sumY] += sandAmount;
						}
						
					}
					
					// 알파자리 구해주기
					// 위치는 가던 방향으로 한칸 더
					int alphaX = nx + dx[d];
					int alphaY = ny + dy[d];
					int alphaAmount = map[nx][ny] - totalSand;
					
					// 범위 벗어나는 경우 모래의 양 합산
					if (alphaX < 0 || alphaX >= n || alphaY < 0 || alphaY >= n) {
						totalOut += alphaAmount;
					}
					else { // 아닌 경우는 기본 모래에 더해주기
						map[alphaX][alphaY] += alphaAmount;
					}
					
					// 다 뿌리고 난 뒤 본래 자리 모래는 0
					map[nx][ny] = 0;
					// 현재 위치 옮겨주기
					currentX = nx;
					currentY = ny;
				}
			}
			
			// 한 사이클 돌고나면 길이값 늘려주기
			for (int i = 0; i< moveCnt.length; i++) {
				moveCnt[i] += 2;
			}
		}
		
		return totalOut;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// tornado
		System.out.println(tornado());

	}

}
