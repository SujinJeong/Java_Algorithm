package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
1. ù��° ã�� 1(�����丶��) �������� �ʺ�켱Ž��
2. 0�� ��� ť�� �߰� (�;���ϴ� ��� ��� ����ϱ� ����)
3. ���� �丶�� �� = ������ ���� �丶�䰪 +1 => �� �ϼ� ����� ���ؼ�
4. ���� �ִ밪�� -1�̸� ���� ���� �丶�䰡 ����, �ִ밪�� 0�̶�� �������� �;��־���, �ƴ� ��� ���ϼ�
 */

public class Q7576 {

	static public int[] dy = { -1, 1, 0, 0 };
	static public int[] dx = { 0, 0, -1, 1 };
	static int min_day = Integer.MIN_VALUE;
	static Queue<Location> q = new LinkedList<Location>();;
	static int N, M;
	static int[][] map;
	
	// 1(���� �丶��) ��ġ ������ ���� ��ü
	static class Location {
		public int x;
		public int y;
		
		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int bfs() {
		while (!q.isEmpty()) {
			Location l = q.poll();
			int cx = l.x;
			int cy = l.y;
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				// Ž���� ��ġ�� ���� ���� �ְ� ������ �丶���
				if (nx < N && nx >= 0 && ny < M && ny >=0 )
					if (map[nx][ny] == 0) {
						q.offer(new Location(nx, ny));
						map[nx][ny] = map[cx][cy] + 1;
					}
			}
		}
		
		
		// �ϼ� ���
		for (int x = 0; x < N; x++)
			for (int y = 0; y < M; y++) {
				// bfs�� �������� ������ �丶�䰡 �ִ� ���
				if (map[x][y] == 0) return -1;
				// �������� ��� �ּ� �ϼ� ���ϱ�
				min_day = Math.max(min_day, map[x][y]);
			}
		
		// �丶�䰡 �̹� �;��ִ� ��쿡�� -1�� 1���θ� �̷���� �����Ƿ�
		if (min_day == 1) return 0;
		// ó���� 1���� �������ϱ� -1���ֱ�
		return min_day-1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// input
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// ù��° index �������� Ž�� ����
				if (map[i][j] == 1) q.offer(new Location(i, j));
			}
		}
		
		// output
		System.out.println(bfs());
	}

}
