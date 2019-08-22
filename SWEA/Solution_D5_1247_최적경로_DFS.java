package Aug0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D5_1247_최적경로_DFS {
	
	static int T, N, min = 1000000000;
	static int customers[][], distances[][];
	static boolean visited[];
	static int[] home = new int[2], company = new int[2];
	
	//DFS
	public static void dfs(int now, int count, int cost) {
		if(cost >= min) return;
		if(count==N) {
			cost += distances[now][1]; //home과 now의 거리
			if(cost < min) min = cost;
			return;
		}
		
		for(int i = 2; i<N+2; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, count+1, cost+distances[now][i]);
				visited[i] = false;
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
			customers = new int[N+2][];
			distances = new int[N+2][N+2];
			visited = new boolean[N+2];
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i<N+2; i++) {
				customers[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			for(int i = 0; i<N+2; i++) {
				for(int j = i+1; j<N+2; j++) {
					int dist = Math.abs(customers[i][0]-customers[j][0])+Math.abs(customers[i][1]-customers[j][1]);
					distances[i][j] = dist;
					distances[j][i] = dist;					
				}
			}
			
			visited[0] = true;
			dfs(0, 0, 0);
			
			System.out.println("#"+t+" "+min);
		}
	}

}
