import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2468_안전영역 {

	static int N; //2<=N<=100
	static int max = Integer.MIN_VALUE;
	static int map[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	static boolean visited[][];
	static boolean checked[] = new boolean[101]; //������ ����(?), 1<=����<=100
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				checked[map[i][j]] = true;
			}
		}		
		
		for(int i = 0; i<100; i++) {
			if(checked[i]) { //map�� ���� ���̸� Ž��
				search(i);
			}
		}
		if(max==0) max = 1;
		System.out.println(max);
	}

	private static void search(int n) {
		
		visited = new boolean[N][N];
		int safe = 0; //���������� ����
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++){
				if(map[i][j] > n && !visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, n);
					safe++;
				}
			}
		}
		if(safe > max) max = safe;
	}

	private static void dfs(int r, int c, int n) {
		
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0 && nr<N && nc>=0 && nc<N
					&& map[nr][nc]>n && !visited[nr][nc]) {
				//n���� �۰ų� ���� ���̸� Ž��
				visited[nr][nc] = true;
				dfs(nr, nc, n);
			}
		}		
		
	}
}
