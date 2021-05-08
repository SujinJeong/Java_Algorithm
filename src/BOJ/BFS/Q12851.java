package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
1. bfs�� ���� ���� = �ð�! �ð��� min���� ũ�� ���̻� �� �ʿ䰡 ����
2. ť���� ���� ������ �湮ó�� (��� ����� ���� ������ ���� ���� �� �湮ó�� �ϴ� ���� �ƴ϶� ����)
3. ���� ���� ���� ��ġ�� ���� ��� min�� ��� ( �ð��� min�� �� ���� �� min���� ����, min���� �ð��� ���ٸ� �ٸ� ����� ���̹Ƿ� cnt ���� )
4. visited�� false�̰� N�� ���� ���� 0~100000���� �ִ� ��� ť�� �ְ� �湮
 */
public class Q12851 {
	static int min = Integer.MAX_VALUE;
	static int cnt = 1;
	static Queue<Info> q = new LinkedList<Info>();
	static int N, K;
	
	public static int bfs() {
		boolean[] visited = new boolean[100001];
		
		while(!q.isEmpty()) {
			Info i = q.poll();
			
			// ��Ҵ� ����!
			if (i.x == K) {
				// ����� ���� 1�� �̻��� ���
				if (min == i.time)
					cnt++;
				// �ּ� �ð� ����
				else min = Math.min(min, i.time);
				// �ٸ� ����� ���� ���� �� �����Ƿ� break�� �ƴ϶� continue
				continue;
			}
			
			visited[i.x] = true;
			
			// ���� ���Ѱ�� ������ �̵�
			// ������� �ν��ϱ� ������ ���� ����� �տ� �־���� �Ѵ�
			if (i.x+1 <= 100000 && !visited[i.x + 1]  )
				q.offer(new Info(i.x+1, i.time+1));
			if (i.x-1 >= 0 && !visited[i.x - 1])
				q.offer(new Info(i.x-1, i.time+1));
			if (i.x*2 <= 100000 && !visited[i.x*2]  )
				q.offer(new Info(i.x*2, i.time+1));
		}
		return min;
	}
	
	public static class Info {
		public int x;
		public int time;
		
		public Info(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// �ʱ���ǥ ť�� push
		q.offer(new Info(N, 0));
		System.out.println(bfs());
		System.out.println(cnt);
	}

}
