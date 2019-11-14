import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {

	static int R, C, T, ans = 0;
	static int map[][];
	static int start[] = new int[2];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int idx = 0;
		
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					start[idx++] = i;
				}
			}
		}
		
		while(--T>=0) {
			spread();
			rotate();
		}
		
		int ans = 0;
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(map[i][j] != -1) {
					ans += map[i][j];
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	private static void spread() {
		int newMap[][] = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				
				//-1이 아닐 때도 검사
				int cnt = 0;
				for(int k = 0; k<4; k++) {
					int nr = i + dr[k]; //여기 index 항상 주의!
					int nc = j + dc[k];
					
					if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]!=-1) {
						cnt++;
						newMap[nr][nc] += map[i][j]/5;
					}
				}
				newMap[i][j] += map[i][j]-(map[i][j]/5)*cnt;					
			}
		}
		
		for(int i = 0; i<R; i++) {
			map[i] = newMap[i].clone();
		}
		
	}

	public static void rotate() {
		
		int newMap[][] = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			newMap[i] = map[i].clone();
		}
		
		//반시계 방향 회전
		int startR = start[0];
		
		for(int r = startR-1; r>0; r--) {
			newMap[r][0] = newMap[r-1][0]; 
		}
		for(int c = 0; c<C-1; c++) {
			newMap[0][c] = newMap[0][c+1];
		}
		for(int r = 0; r<startR; r++) {
			newMap[r][C-1] = newMap[r+1][C-1];
		}
		for(int c = C-1; c>1; c--) {
			newMap[startR][c] = newMap[startR][c-1];
		}
		newMap[startR][1] = 0;
		
		//시계 방향 회전
		startR = start[1];
		
		for(int r = startR+1; r<R-1; r++) {
			newMap[r][0] = newMap[r+1][0];
		}
		for(int c = 0; c<C-1; c++) {
			newMap[R-1][c] = newMap[R-1][c+1];
		}
		for(int r = R-1; r>startR; r--) {
			newMap[r][C-1] = newMap[r-1][C-1];
		}
		for(int c = C-1; c>1; c--) {
			newMap[startR][c] = newMap[startR][c-1];
		}
		newMap[startR][1] = 0;
		
		for(int i = 0; i<R; i++) {
			map[i] = newMap[i].clone();
		}
		
	}	

}
