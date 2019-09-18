import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_빵집 {
	
	static int R, C;
	static char map[][];
	static boolean visited[][];
	static int dr[] = {-1, 0, 1}, dc[] = {1, 1, 1}; //**우선순위-오른쪽위,오른쪽,오른쪽아래
	public static int count = 0;
	public static boolean flag = false;

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i<R; i++) {
			String str = bf.readLine();
			for(int j = 0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		go();
		
		System.out.println(count);
	}
	

	private static void go() {
		
		for(int r = 0; r<R; r++) { //무조건 첫째 열부터 시작(r,0)
			flag = false; //초기화

			for(int i = 0; i<3; i++) {
				int nr = r + dr[i];
				int nc = 1;
				if(nr>=0 && nr<R && map[nr][nc]=='.' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc);
				}
				if(flag) break; //(r,0)에서 이미 도착경로를 찾았으면 더 탐색 ㄴㄴ (r+1,0)으로 넘어감
			}
		}
	}
	
	private static void dfs(int r, int c) {
		if(flag) return;
		
		if(c==C-1) { //마지막 열에 도착했을 경우 return
			flag = true;
			count++; return;
		}
		
		for(int i = 0; i<3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0 && nr<R && nc>=0 && nc<C
					&& map[nr][nc]=='.' && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc); //visited를 풀어줄 필요가 없당
				if(flag) break; //경로를 이미 찾았으면 더이상 dfs ㄴㄴ
			}
		}
		
	}

}
