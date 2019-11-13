package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	
	private static int map[][], chk[][];
	private static int dr[] = {0, 1, 1}, dc[] = {1, 1, 0}; //오른쪽, 오른쪽 아래, 아래
	private static int dir[][] = {{0, 1}, {0, 1, 2}, {1, 2}}; //가로일 때 다음 가능 방향(가로, 대각선), 세로일 때 다음 방향(대각선, 세로), 대각선일 때 다음 방향
	private static int answer, N;
	
	public static class Pair {
		int r, c, dir, num;
		public Pair(int r, int c, int dir, int num) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		chk = new int[N][N];
		chk[0][0] = 1;
		chk[0][1] = 1;
		
		//입력
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		go(0, 1, 0); //현재 상태 (0, 1, 가로)

		System.out.println(answer);
	}

	private static void go(int r, int c, int now) {
		if(r==N-1 && c==N-1) {
			answer++;
			return;
		}
		
		int nr, nc;
		for(int i = 0; i<dir[now].length; i++) {
			int next = dir[now][i];
			nr = r + dr[next];
			nc = c + dc[next];
			if(nr>=0 && nr<N &nc>=0 && nc<N
					&& map[nr][nc]==0) {
				if(next == 1 && (map[nr][c]!=0 || map[r][nc]!=0))
					continue;
				chk[nr][nc] = 1;
				go(nr, nc, next);
				chk[nr][nc] = 0;
			}
		}		
	}

}







