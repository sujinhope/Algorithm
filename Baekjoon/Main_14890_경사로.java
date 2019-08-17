package Aug17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int x;
	boolean b;
	
	Pair(int x, boolean b){
		this.x = x;
		this.b = b;
	}	
}

public class Main_14890_경사로 {

	static int N, L, count = 0;
	static int map[][];
	static List<Pair>[] listArr, newListArr;
	
	public static boolean check(int index, List<Pair> list) {
		int cnt = 0;
		int height = list.get(index).x - 1;
		for(int i = index + 1; i<list.size(); i++) {
			if(cnt==L)
				break;
			int h = list.get(i).x;
			boolean b = list.get(i).b;
			if(!b && h == height) {
				list.set(i, new Pair(h, true));
				cnt++; //경사로를 세울 수 있다면
			} else { //높이가 1이상이거나 
				return false;
			}
		}
		if(cnt != L) {
			for(int i = index + 1; i<list.size(); i++) {
				if(i>=index+1+L) break;
				int h = list.get(i).x;
				list.set(i, new Pair(h, false)); //경사로를 못세울 경우  visited 다시 돌려놓기
			}
			return false;
		}
		return true;
	}
	
	public static boolean go(List<Pair> list) { //중간에 height 갱신!!!!!!!!!!!!!!!!!
		int height = list.get(0).x; //한 행/열
		int cnt = 1;
		for(int i = 0; i<list.size(); i+=cnt) {
			List<Pair> newList = new ArrayList<>();
			cnt = 1;
			int gap = height - list.get(i).x; //이전블록과의 높이 차
			if(gap < -1 || gap > 1) { //이전 블록과 높이차가 1 이상일 때
				return false;
			} else if(gap == -1) { //현재 높이가 이전 높이보다 1 높을 때
				newList.addAll(list);
				Collections.reverse(newList);
				if(!check(N-1-i, newList)) 
					return false;
				Collections.reverse(newList);
				for(int j = 0; j<newList.size(); j++) { //visited 결과 반영해주기
					list.set(j, newList.get(j));
				}
				height++;
			} else if(gap == 1) { //현재 높이가 이전 높이보다 1 낮을 때
				if(!check(i - 1, list)) return false; //else if(gap==-1)일 때와 조건 똑같이 해주려면 i-1로 해줘야 함.
				height--; //높이 맞춰주기
				cnt = L; //원래 진행방향일 경우만 index 건너뛰기
			} //높이가 같을 경우는 pass
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[N];
		newListArr = new ArrayList[N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			listArr[i] = new ArrayList<Pair>();
			newListArr[i] = new ArrayList<Pair>();
			for(int j = 0; j<N; j++) {
				listArr[i].add(new Pair(Integer.parseInt(st.nextToken()), false)); //한 행
				newListArr[i].add(new Pair(0, false));
			}			
		}
		
		//좌-우/상-하 =>전치행렬 관계
		//한 길에서 높이가 증가할 경우->method에 reverse(list)로 매개변수 이용.
		
		//좌-우 탐색
		for(int i = 0; i<N; i++) {
			if(go(listArr[i])) 
				count++; //길이 가능할 경우
		}
		
		//Transpose
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				newListArr[i].set(j, new Pair(listArr[j].get(i).x, false)); //Pair인데 이렇게 복사하는게 가능할까?
			}			
		}
		//상-하 탐색
		for(int i = 0; i<N; i++) {
			if(go(newListArr[i]))
				count++; //길이 가능할 경우
		}
		
		System.out.println(count);
	}

}
