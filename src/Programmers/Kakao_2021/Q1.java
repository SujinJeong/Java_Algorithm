package Programmers.Kakao_2021;

import java.util.HashMap;

public class Q1 {
	
	static class Solution {
	    public int solution(int[] gift_cards, int[] wants) {
	        int answer = 0;
	        HashMap<Integer, Integer> duplicate_count1 = new HashMap<Integer, Integer>();
	        HashMap<Integer, Integer> duplicate_count2 = new HashMap<Integer, Integer>();
	        
	        for(int i = 0 ; i < gift_cards.length ; i++){
	            if (duplicate_count1.get(gift_cards[i]) != null ) {
	                duplicate_count1.put(gift_cards[i], duplicate_count1.get(gift_cards[i])  + 1);
	            } else {
	                duplicate_count1.put(gift_cards[i] , 1);
	            }
	        }
	        
	        for(int i = 0 ; i < wants.length ; i++){
	            if (duplicate_count2.get(wants[i]) != null) {
	                duplicate_count2.put(wants[i], duplicate_count2.get(wants[i])  + 1);
	            } else {
	                duplicate_count2.put(wants[i] , 1);
	            }
	        }
	        
	        for (int i = 0; i < wants.length; i++) {
	        	if (duplicate_count1.get(wants[i]) != null) {
	        		int gift_cards_cnt = duplicate_count1.get(wants[i]);
	        		int wants_cnt = duplicate_count2.get(wants[i]);
	        		
	        		// 갯수가 다를 경우
	        		if (gift_cards_cnt != wants_cnt) {
	        			answer += Math.abs(gift_cards_cnt - wants_cnt);
	        			
	        			// 중복 계산 방지 위해 계산 후 hash에서 제거
	        			duplicate_count1.remove(wants[i]);
	        			duplicate_count2.remove(wants[i]);
	        		}
	        	}
	        }
	        
	        return answer;
	    }
	}
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] gift_cards = {1,1,1,1,1};
		int[] wants = {9,9,9,99,9};
		System.out.println(sol.solution(gift_cards, wants));
	}

}
