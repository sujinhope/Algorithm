package GitUpload;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_14888_연산자끼워넣기 { // + - * /
	
	static int N, min = 1000000001, max = -1000000001;
	static List<Integer> oper;
	static List<Integer> A, visit; //생성하기(new)
	
	public static int calculate(List<Integer> list) {
		int result = A.get(0);
		for(int i = 0; i<N - 1; i++) {
			switch(list.get(i)) {
			case 0: //+				
				result += A.get(i + 1); break;
			case 1: //-
				result -= A.get(i + 1); break;
			case 2: //*
				result *= A.get(i + 1); break;
			case 3:
				result /= A.get(i + 1); break;
			}
		}		
		return result;
	}
	
	public static void go(int cnt, List<Integer> list) {
		if(cnt >= N - 1) {
			int result = calculate(list);
			if(result < min) min = result;
			if(result > max) max = result;
			return;
		}
		for(int i = 0; i<oper.size(); i++) {
			if(visit.get(i) != 1) {
				visit.set(i, 1);
				list.add(oper.get(i)); //순열
				go(cnt + 1, list);
				list.remove(list.size() - 1); //리스트에 추가한 원소를 다시 제거해 줘야 한다.
				visit.set(i,  0);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		oper = new ArrayList<>();
		visit = new ArrayList<>();
		A = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			A.add(sc.nextInt());
		}
		for(int i = 0; i<4; i++) {
			int a = sc.nextInt();
			for(int j = 0; j<a; j++) {
				oper.add(i); //+ - x /
				visit.add(0);
			}
		}
		List<Integer> lst = new ArrayList<Integer>();
		go(0, lst);
		
		System.out.println(max);
		System.out.println(min);
	}

}
