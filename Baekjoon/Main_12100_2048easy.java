package Baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12100_2048easy {
	
	static int N, max = 0;
	static int[][] map;
	
	static void go(int index, int[][] map, int cnt) { //1,2,3,4-상,하,좌,우
		if(cnt == 5)
			return;
		
		int newMap[][] = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			List<Integer> list = new ArrayList<>();
			for(int j = 0; j<N; j++) {
				if(index <= 2) { //위, 아래 방향일 경우는 열단위로 탐색
					if(map[j][i] != 0) list.add(map[j][i]);
				} else {
					if(map[i][j] != 0) list.add(map[i][j]);
				}				
			}
			
			if(index==2||index==4) { //아래, 오른쪽 방향일 때는 reverse
				Collections.reverse(list);
			}
			
			List<Integer> plusList = new ArrayList<>();
			for(int j = 0; j<list.size(); j++) {
				if(j == list.size() - 1)
					plusList.add(list.get(j));
				else {
					if (list.get(j).equals(list.get(j + 1))) {
						//똑같은 수가 연속으로 있을 경우 *2를 해서 list에 넣고, j++
						plusList.add(2 * (list.get(j++)));
					} else {
						plusList.add(list.get(j));
					}
				}
				if(plusList.get(plusList.size() - 1) > max)
					max = plusList.get(plusList.size() - 1);
			}
			
			//결과를 update할 newMap
			for(int j = 0; j<plusList.size(); j++) {
				if(index == 1) {
					newMap[j][i] = plusList.get(j);
				} else if(index == 2) {
					newMap[N - j - 1][i] = plusList.get(j);
				} else if(index == 3) {
					newMap[i][j] = plusList.get(j);
				} else {
					newMap[i][N - j - 1] = plusList.get(j);
				}
			}			
		}	
		for(int i = 1; i<=4; i++) {
			go(i, newMap, cnt + 1);
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i<=4; i++) {
			go(i, map, 0);
		}

		System.out.println(max);
		
	}

}
