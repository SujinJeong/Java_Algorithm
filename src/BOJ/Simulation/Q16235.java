package BOJ.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16235 {

	static int[] dx = { 1, 0, -1, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
	static int[][] food, map;
	static int n;
	static PriorityQueue<Tree> tree;

	static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
//			if (this.x == o.x && this.y == o.y) {
//				return this.age - o.age;
//			}
			return this.age - o.age;
		}

	}

	private static void solve() {
		Queue<Tree> deadTree = new LinkedList<Tree>(); // 죽은 나무 저장 배열
		PriorityQueue<Tree> newTree = new PriorityQueue<>(); // 새로운 나무 정보 저장

		// 봄 - 나이가 어린 나무부터 자신의 나이만큼 양분먹음(이 때 칸에 있는 양분이 없으면 양분 못먹은 나무는 죽음), 나이 +1,
		while (!tree.isEmpty()) {
			Tree cur = tree.poll();
			// 양분 먹을 수 있는지 확인
			if (map[cur.x][cur.y] >= cur.age) {
				map[cur.x][cur.y] -= cur.age;
				newTree.add(new Tree(cur.x, cur.y, cur.age + 1));
			} // 양분을 먹을 수 없으면
			else {
				deadTree.add(new Tree(cur.x, cur.y, cur.age));
			}

		}
		// 여름 - 죽은 나무가 양분으로 변함, map[r][c] += 죽은 나무 나 / 2
		while (!deadTree.isEmpty()) {
			Tree cur = deadTree.poll();
			map[cur.x][cur.y] += cur.age / 2;
		}

		// 최종 나무에 살아남은 나무 추가
		for (Tree t : newTree) {
			tree.add(t);
		}

		// 가을 - 나무 나이가 5배수이면 인접한 8개 칸에 나이가 1인 나무생성(유효 범위 체크)
		while (!newTree.isEmpty()) {
			Tree cur = newTree.poll();
			if (cur.age % 5 == 0) {
				for (int d = 0; d < 8; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;
					// 나이가 1인 나무 생성
					tree.add(new Tree(nx, ny, 1));
				}
			}
		}

		// 겨울 - 입력 양분만큼 추가
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += food[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = 5; // 초기 양분은 모두 5
			}
		}
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 양분 정보
		food = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 상도가 심은 나무 정보
		tree = new PriorityQueue<Tree>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree.add(new Tree(r-1, c-1, age));
		}

		// 봄, 여름, 가을, 겨울 k번 반복
		while (k-- > 0) {
			solve();
		}
		
		System.out.println(tree.size());
	}

}
