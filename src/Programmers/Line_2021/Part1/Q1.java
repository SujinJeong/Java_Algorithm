package Programmers.Line_2021.Part1;

public class Q1 {
	static class Info {
		int pre;
		String lan;

		public Info(String lan, int pre) {
			super();
			this.pre = pre;
			this.lan = lan;
		}

	}

	static class Solution {
		public String solution(String[] table, String[] languages, int[] preference) {
			String answer = "";
			int max = Integer.MIN_VALUE;
			
			// 개발자 정보
			Info[] dev = new Info[languages.length];
			for (int i = 0; i < languages.length; i++)
				dev[i] = new Info(languages[i], preference[i]);
			
			for (int i = 0; i < table.length; i++) {
				String[] rt = table[i].split(" ");
				int sum = 0;
				for (int j = 1; j <= 5; j++) {
					for (int k = 0; k < dev.length; k++) {
						if (dev[k].lan.equals(rt[j]))
							sum += dev[k].pre * (6-j);
					}
				}
				if (sum > max) {
					max = sum;
					answer = rt[0];
				}
				else if (max == sum) {
					if (answer.compareTo(rt[0]) > 0)
						answer = rt[0];
				}
			}
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] languages = {"JAVA", "JAVASCRIPT"};
		int[] preference = {7,5};
		System.out.println(sol.solution(table, languages, preference));
	}

}
