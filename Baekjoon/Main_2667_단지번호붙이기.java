import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_2667_단지번호붙이기 {
	
	static int N, cnt = 0;
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	static int map[][];
	static boolean visited[][];
	static List<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i<N; i++) {
			String s = bf.readLine();
			for(int j = 0; j<N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}	
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					cnt = 0;
					visited[i][j]=true;
					dfs(i, j);
					result.add(cnt);
				}
			}
		}
		System.out.println(result.size());
		Collections.sort(result);
		for(int i = 0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	private static void dfs(int x, int y) {
		cnt++;
		visited[x][y] = true;
		
		for(int i = 0; i<4; i++) {
			int nx = x + dr[i];
			int ny = y + dc[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N
					&& map[nx][ny]==1 && !visited[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}

}
