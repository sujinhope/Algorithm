import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행 { //190829
	static int T, R, C, max;
	static char map[][];
	static boolean chk[] ; //알파벳
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	
	public static void go(int r, int c, int cnt) {
		if(cnt > max) max = cnt;
		if(cnt==26) return;
		
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0 && nr<R && nc>=0 && nc<C && !chk[map[nr][nc]-65]) {
				chk[map[nr][nc] - 65] = true;
				go(nr, nc, cnt + 1);
				chk[map[nr][nc] - 65] = false;
			}
		}		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t<=T; t++) {
			max = -1;
			st = new StringTokenizer(bf.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			chk = new boolean[26];
			
			String str;
			for(int i = 0; i<R; i++) {
				str = bf.readLine();
				for(int j = 0; j<C; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			chk[map[0][0]-65] = true;
			go(0, 0, 1);
			System.out.println("#"+t+" "+max);
		}
	}
}
