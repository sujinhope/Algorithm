import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기2_DP {
	
	private static int map[][];
	private static int N;
	
	private static Pair dp[][];
	
	public static class Pair {
		long garo = 0L, sero = 0L, daegak = 0L;
		public Pair(long garo, long sero, long daegak) {
			this.garo = garo;
			this.sero = sero;
			this.daegak = daegak;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		dp = new Pair[N][N];
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				dp[i][j] = new Pair(0, 0, 0);
			}
		}
		
		//입력
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i<N; i++) {
			if(map[0][i]!=0) break;
			dp[0][i].garo = 1L;
		}
		
		for(int i = 1; i<N; i++) {
			for(int j = 2; j<N; j++) {
				if(map[i][j]!=0) continue;
				
				Pair now = dp[i][j];
				//가로
				if(map[i][j-1]==0) {
					now.garo = dp[i][j-1].daegak + dp[i][j-1].garo;
					
					//대각선
					if(map[i-1][j]==0 && map[i-1][j-1]==0) {
						now.daegak = dp[i-1][j-1].daegak + dp[i-1][j-1].garo + dp[i-1][j-1].sero;
					}
				}
				
				//세로
				if(map[i-1][j]==0) {
					now.sero = dp[i-1][j].daegak + dp[i-1][j].sero;
				}
			}
		}
		Pair answer = dp[N-1][N-1];
		System.out.println(answer.garo + answer.sero + answer.daegak);
	}


}







