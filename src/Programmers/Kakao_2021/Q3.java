package Programmers.Kakao_2021;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Q3 {
	
	static class Info implements Comparable<Info> {
		int to;
		int cost;
		
		public Info(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}


		@Override
		public int compareTo(Info o) {
			return o.cost - cost;
		}

	}
	
	static class Solution {
		public int[] solution(int n, int[] passenger, int[][] train) {
			int[] answer = {};

			ArrayList<ArrayList<Info>> graph = new ArrayList<ArrayList<Info>>();
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<Info>());
			}
			
			for (int i = 0; i < train.length; i++) {
				graph.get(train[i][0]).add(new Info(train[i][1], passenger[i]));
				graph.get(train[i][1]).add(new Info(train[i][0], passenger[i]));
			}
			
			int[] d = new int[n];
			// 양방향
			boolean[] visited = new boolean[n];
			
			dijk(d);
			return answer;
		}
	}

	private static void dijk(int[] d) {
		for (int i = 1; i <= d.length; i++) {
			d[i] = 987654321;
		}
		
		d[1] = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(d[1], 1));

	}
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int n = 6;
		int[] passenger = { 1, 1, 1, 1, 1, 1 };
		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 3, 5 }, { 3, 6 } };
		System.out.println(sol.solution(n, passenger, train));
	}
}
