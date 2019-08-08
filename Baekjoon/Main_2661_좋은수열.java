package Baekjoon;
import java.util.Scanner;

public class Main_2661_좋은수열 {
	static boolean finish;
	static int N;
	
	//좋은 수열인지 아닌지를 찾아주는 메서드
	static boolean find(int len, String s) {
		for(int i = 1; i<=len/2; i++) {
			String front = s.substring(len - i*2, len - i);
			String back = s.substring(len - i, len);
			if(front.equals(back)) {
				return true;
			}
		}
		return false;
	}
	
	static void go(int len, String s) {
		if(finish) return;
		if(find(len, s)) return; //좋은 수열일 경우
		if(len == N) {
			finish = true;
			System.out.println(s);
			return;
		}
		
		for(int i = 1; i<=3; i++) {
			go(len+1, s+i);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		go(1, "1"); //최소값을 가지는 좋은 수열의 경우 첫 번째 숫자는 무조건 1이다.

	}
}
