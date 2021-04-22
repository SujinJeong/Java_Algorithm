package Programmers.Line_2021.Part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Q2 {

	static class Solution {
		public int[] solution(String inp_str) {
			int[] answer = {};
			ArrayList<Integer> arr = new ArrayList<>();
			boolean isT = false;

			// 3
			int cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0;

			if (inp_str.length() < 8 || inp_str.length() > 15)
				arr.add(1);

			for (int i = 0; i < inp_str.length(); i++) {
				char c = inp_str.charAt(i);

				// 2
				if (c == '(' || c == ')' || c == '-' || c == '_' || c == '=' || c == '+')
					isT = true;

				if (Character.isUpperCase(c))
					cnt1 = 1;
				else if (Character.isLowerCase(c))
					cnt2 = 1;
				else if (48 <= c && c <= 57)
					cnt3 = 1;
				else if (c == '~' || c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&'
						|| c == '*')
					cnt4 = 1;
			}

			// 2
			if (isT)
				arr.add(2);

			// 3
			if ((cnt1 + cnt2 + cnt3 + cnt4) < 3)
				arr.add(3);

			// 4
			int o = 0, d = 0, p = 0, n = 0, limit = 4;
			boolean isC = false;
			for (int i = 0; i < inp_str.length(); i++) {
				char tempVal = inp_str.charAt(i);
				if (i > 0 && (p = o - tempVal) > -2 && (n = p == d ? n + 1 : 0) > limit - 3) {
					isC = true;
				}
				d = p;
				o = tempVal;
			}
			if (isC)
				arr.add(4);

			// 5
			if (Pattern.compile("(\\w)\\1\\1\\1\\1").matcher(inp_str).find())
				arr.add(5);

			if (arr.size() == 0)  {
				answer = new int[1];
				answer[0] = 0;
			}
			else {
				Collections.sort(arr);
				answer = new int[arr.size()];
				for (int i = 0; i < answer.length; i++)
					answer[i] = arr.get(i).intValue();
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String inp_str = "CaCbCgCdC888834A";
		for (int i : sol.solution(inp_str))
			System.out.print(i + " ");
	}

}
