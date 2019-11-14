import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	
	static int N, M, max = -1, min=10000;
	static int map[][];
	static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1}; //북동남서
	static List<Pair> cctvs = new ArrayList<>();
	static int move[][][] = {	{{0},{1},{2},{3}} //1번 cctv가 볼 수 있는 방향 수
								, {{0, 2}, {1, 3}} //2번 cctv가 볼 수 있는 방향 수
								, {{0, 1}, {1, 2}, {2, 3}, {3, 0}} //3번
								, {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}} //4번
								, {{0, 1, 2, 3}}};	//5번
	static class Pair {
		int x, y, num;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pair(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvs.add(new Pair(0, 0, 0)); //그냥 0인덱스를 채우기 위한 값
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>=1 && map[i][j]<=5)
					cctvs.add(new Pair(i, j, map[i][j]));
			}
		}		
		go(1, map);
		System.out.println(min);
	}
	
	public static void go(int n, int[][] recMap) { //recursiveMap
		//종료조건
		if(n==cctvs.size()) {
			//min갱신 조건
			int count = 0;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(recMap[i][j]==0) {
						count++;
					}
				}
			}
			if(min > count) min = count;
			return;
		}		
		
		int x = cctvs.get(n).x;
		int y = cctvs.get(n).y;
		int num = cctvs.get(n).num-1; //cctv번호는 1~5, cctvs의 index는 0~4이므로
		
		int newMap[][] = new int[N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				newMap[i][j] = recMap[i][j];
			}
		}
		
		for(int i = 0; i<move[num].length; i++) {//num번 cctv가 볼 수 있는 방향의 개수
			
			List<Pair> list = new ArrayList<>();
			
			for(int j = 0; j<move[num][i].length; j++) { //num번 cctv가 i번 방향을 보는 경우
				int nx, ny;
				int idx = move[num][i][j];
				int cnt = 1;
				while (true) {
					nx = x + dr[idx] * cnt;
					ny = y + dc[idx] * cnt;
					cnt++;
					if (nx < 0 || nx >= N || ny < 0 || ny >= M 
							|| newMap[nx][ny] == 6) {
						break;
					} else if (newMap[nx][ny] == 0) {
						newMap[nx][ny] = '#'; //'#' num+1
						list.add(new Pair(nx, ny));
					}
				}
			}

			go(n+1, newMap);
			
			for(int j = 0; j<list.size(); j++) {
				int r = list.get(j).x;
				int c = list.get(j).y;
				newMap[r][c] = 0;
			}
			list.clear();
		}		
		
	}

}
