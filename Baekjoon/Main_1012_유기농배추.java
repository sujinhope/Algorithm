import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
	static int T, N, M, K, map[][], worm = 0, visit[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	
	static void dfs(int r, int c) {		
		for(int d = 0; d<4; d++) {
			int nr = r + dr[d]; //열
			int nc = c + dc[d]; //행
			if(nc>=0 && nc<M && nr>=0 && nr<N 
					&& map[nr][nc]==1 && visit[nr][nc]==0) {
				visit[nr][nc] = 1;
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		
		int r, c;
		
		for(int t = 1; t<=T; t++) {
			worm = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new int[N][M];
			
			for(int k = 0; k<K; k++) {
				st = new StringTokenizer(bf.readLine());
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				map[c][r] = 1;
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j] == 1 && visit[i][j] != 1) {
						visit[i][j] = 1;
						worm++;
						dfs(i, j);
					}
				}
			}
			System.out.println(worm);
		}		
	}
}