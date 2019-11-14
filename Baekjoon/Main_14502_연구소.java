package September.Sep04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	//flag 설정
	static int N, M;
	static int map[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, 1, -1};
	
	//static int selected[] = new int[3]; //선택된 세 개의 벽 위치
	static int arr[]; //N*M (칸의 개수)
	static boolean visited[];
	
	//current. => 시간 재는 법
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arr = new int[N*M];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = i;
		}
		
		for(int i = 0; i<N; i++){
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		visited = new boolean[N*M];
		
		perm(list);
		
		System.out.println(max);		
	}
	
	/* 이렇게 하는 법도 공부하기
	private static void perm(int step) {
		if(!finish) return;
		if(step==3) {
			flag = false;
			spread(); 
			if(!flag) finish = false;
			return;
		}
		
		for(int i = step; i<arr.length; i++) {
			
		}
		
	}
	*/
	private static int max = -1;
	
	private static void perm(List<Integer> list) {
		
		if(list.size()==3) { //선택된 위치에 벽 세우기
			for(int i = 0; i<list.size(); i++) {
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				
				if(map[r][c] != 0) 
					return; //벽을 못 세우는 경우는 확산ㄴㄴ
			}
			for(int i = 0; i<list.size(); i++) {
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				map[r][c] = 1; //1이 벽
			}
			
			spread();
			
			for(int i = 0; i<list.size(); i++) { //맵 원상복귀
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				map[r][c] = 0;
			}
			return;
		}
		
		for(int i = 0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				perm(list);
				list.remove(list.size()-1);
				visited[i] = false;				
			}
		}
		
	}
	
	private static void spread() {
		
		int newMap[][][] = new int[2][N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				newMap[1][i][j] = newMap[0][i][j] = map[i][j];
			}
		}
		
		int idx = 0;
		
		while(true) {
			// 확산 한 번
			boolean flag = false;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(newMap[(idx+1)%2][i][j]==2) {
						for(int k = 0; k<4; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if(nr>=0 && nr<N && nc>=0 && nc<M
									&& newMap[(idx+1)%2][nr][nc]==0) {
								newMap[idx%2][nr][nc] = 2;
								flag = true;
							}
						}
					}
				}
			}
			
			//확산 하고나면 다시 복사
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					newMap[(idx+1)%2][i][j] = newMap[idx%2][i][j];
				}
			}
			
			if(flag == false) break;
			
			idx++;
		}
		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				//idx상관 ㄴㄴ 어차피 위에서 둘다 똑같이 복사했으므로
				if(newMap[idx%2][i][j]==0) sum++;
//				sum += newMap[idx%2][i][j];
			}
		}
		
		if(sum > max) 
			max = sum;
		
	}

}
