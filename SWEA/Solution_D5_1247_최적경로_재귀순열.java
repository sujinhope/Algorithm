package Aug0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D5_1247_최적경로_재귀순열 {
	
	static int T, N, min = 1000000000;
	static int customers[][];
	static boolean visited[];
	static int[] home = new int[2], company = new int[2];
	
	//일반 재귀순열
	public static void go(int count, int bx, int by, int visited, int cost) {
		if(cost >= min) return; //가지치기
		if(count == N) {
			cost += Math.abs(bx-home[0])+Math.abs(by-home[1]);
			if(cost < min) min = cost;
			return;
		}
		for(int i = 0; i<N; ++i) {
			if((visited & (1<<i)) == 0) {
				int dist = Math.abs(bx-customers[i][0])+Math.abs(by-customers[i][1]);
				go(count+1, customers[i][0], customers[i][1], visited | (1<<i), cost+dist);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		
		for(int t = 1; t<=T; t++) {
			min = 1000000000;
			N = Integer.parseInt(bf.readLine());
			customers = new int[N][];
			visited = new boolean[N];
			
			st = new StringTokenizer(bf.readLine());
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			for(int i = 0; i<N; i++) {
				customers[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			go(0, company[0], company[1], 0, 0);
			
			System.out.println("#"+t+" "+min);
		}
	}

}
