import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1861_정사각형방 {
	static int N, ans = 0, count = 0, max, maxNum;
	static int map[][];
	static int visited[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(bf.readLine());
			
			map = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
				for(int j = 0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}			
			
			max = Integer.MIN_VALUE;
			maxNum = Integer.MAX_VALUE;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					count = 0;
					visited = new int[N][N];
					dfs(i, j, 1);
					if(count > max) {
						max = count;
						maxNum = map[i][j];
					} else if(count == max) {
						if(maxNum > map[i][j]) maxNum = map[i][j];
					}
				}
			}			
			
			System.out.println("#"+t+" "+maxNum+" "+max);
		}
	}

	private static void dfs(int r, int c, int n) {
		
		if(n > count) count = n;
		
		visited[r][c] = 1;
		
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0 && nr<N && nc>=0 && nc<N
					&& map[nr][nc]-map[r][c]==1 && visited[nr][nc]!=1) {
				dfs(nr, nc, n+1);
			}
		}		
	}

}
