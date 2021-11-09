package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15686 {
	public static int min = Integer.MAX_VALUE;
	public static ArrayList<Building> house = new ArrayList<>();
	public static ArrayList<Building> chicken = new ArrayList<>();
	public static boolean[] visited;

	public static class Building {
		public int x, y;

		public Building(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void Combination(ArrayList<Building> arr, boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			int total = 0;

			for (int j = 0; j < house.size(); j++) {
				int val = Integer.MAX_VALUE;
				for (int i = 0; i < chicken.size(); i++) {
					// �湮�Ѱ�� = M���� �ɸ�
					if (visited[i])
						val = Math.min(val,
								Math.abs(house.get(j).x - chicken.get(i).x) + Math.abs(house.get(j).y - chicken.get(i).y));
				}
				total += val;
			}
			min = Math.min(min, total);
			return;
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			Combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		// map input
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
				if (map[i][j] == 1) 
					house.add(new Building(i, j));
				else if (map[i][j] == 2) 
					chicken.add(new Building(i, j));
			}
		}

		visited = new boolean[chicken.size()];
		Combination(chicken, visited, 0, chicken.size(), M);

		sb.append(min);
		System.out.println(sb);
	}

}
