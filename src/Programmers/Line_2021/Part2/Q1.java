package Programmers.Line_2021.Part2;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
	static class Solution {

		public static boolean checkNum(String nfat) {
			for (int k = 0; k < nfat.length(); k++) {
				char c = nfat.charAt(k);
				if (48 > c || c > 57) {
					return false;
				}
			}
			return true;
		}

		public static boolean checkString(String nfat) {
			for (int k = 0; k < nfat.length(); k++) {
				char c = nfat.charAt(k);
				// 대문자 A
				if (c < 65) {
					return false;
				}
				// 대문자와 소문자 사이 값
				else if (c > 90 && c < 97) {
					return false;
				}
				// 소문자 z
				else if (c > 122) {
					return false;
				}
			}
			
			return true;
		}

		public boolean[] solution(String program, String[] flag_rules, String[] commands) {
			boolean[] answer = new boolean[commands.length];
			Map<String, String> map = new HashMap<String, String>();
			Map<String, Integer> map_cnt;
			String[] fas = new String[flag_rules.length];
			
			for (int i = 0; i < flag_rules.length; i++) {
				String[] line1 = flag_rules[i].split(" ");
				String fa = line1[0];
				String fat = line1[1];
				fas[i] = line1[0];
				
				map.put(fa, fat);
			}

			for (int i = 0; i < commands.length; i++) {
				// flag가 몇번 사용되었는지 담기 위한 배열
				map_cnt = new HashMap<String, Integer>();
				
				for (String s : fas) {
					map_cnt.put(s, 0);
				}

				String[] line2 = commands[i].split(" ");
				// 유요한 명령인지 체크
				boolean flag = true;

				// 1
				if (!line2[0].equals(program)) {
					answer[i] = false;
					continue;
				}

				int j = 1;
				// commands의 모든 문자 검사
				loop: while (j < line2.length) {
					// flag argument 만나면
					if (line2[j].contains("-")) {
						// flag argument 만났는데 저장되어 있는 key인 경우
						if (map.containsKey(line2[j])) {
							// flag 호출 cnt 증가
							map_cnt.put(line2[j], map_cnt.get(line2[j])+1);

							// flag_argument_type 검사
							if (j+1 >= line2.length) break;
							String nfat = line2[j + 1];

							// 2
							if (map.get(line2[j]).equals("NUMBER")) { // NUMBER
								if (!checkNum(nfat)) {
									flag = false;
									break loop;
								}
								j += 2;
							} else if (map.get(line2[j]).equals("STRING")) { // STRING
								if (!checkString(nfat)) {
									System.out.println("check string false");
									flag = false;
									break loop;
								}
								j += 2;
							} else { // NULL
								j++;
							}
						}
						
						// flag argument 만났는데 저장되어 있는 key인 경우
						// 즉, 4
						else {
							flag = false;
							break;
						}
					} else { // 명령어를 만나지 못한 경우
						flag = false;
						break;
						// end of checking flag
					}
					
				} // end of while
				// 3
				for (String key : map.keySet())
					if (map_cnt.get(key) > 1)
						flag = false;

				answer[i] = flag == true? true: false;
			} // end of commands for

			return answer;

		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String program = "line";
		String[] flag_rules = { "-s STRING", "-n NUMBER", "-e NULL" };
		String[] commands = { "line -s 123 -n HI", "line fun" };
		for (boolean b : sol.solution(program, flag_rules, commands))
			System.out.print(b + " ");
	}

}
