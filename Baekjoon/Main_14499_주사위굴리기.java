package Aug8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
	
	static int N, M, x, y, K;
	
	public static class Dice{
		int left,right,top,bottom,front,back;
		Dice() {
			left = 0; right = 0; top = 0; bottom = 0; front = 0; back = 0;
		}
		public Dice(int left, int right, int top, int bottom, int front, int back) {
			this.left = left;
			this.right = right;
			this.top = top;
			this.bottom = bottom;
			this.front = front;
			this.back = back;
		}		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] arrK = new int[K];
		int[] dr = {0, 0, -1, 1}; //동서북남
		int[] dc = {1, -1, 0, 0};
		
		Dice dice = new Dice();
		
		//map입력받기
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//명령 입력받기
		st = new StringTokenizer(bf.readLine());
		int nr = x, nc = y;
		for(int i = 0; i<K; i++) {
			int k = Integer.parseInt(st.nextToken());
			int r = nr, c = nc;
			
			int top = dice.top, bottom = dice.bottom, left = dice.left, right = dice.right;
			int front = dice.front, back = dice.back;
			
			if(k == 1) { //동
				nr += dr[0];	nc += dc[0];
				if(nr < 0 || nr >=N || nc < 0 || nc>=M) {
					nr = r; nc = c; //nr, nc 초기화하고
					continue;
				}
				dice.left = bottom;
				dice.bottom = right;
				dice.right = top;
				dice.top = left;
			} else if(k == 2) { //서
				nr += dr[1];	nc += dc[1];
				if(nr < 0 || nr >=N || nc < 0 || nc>=M) {
					nr = r; nc = c; //nr, nc 초기화하고
					continue;
				}
				dice.left = top;
				dice.top = right;
				dice.right = bottom;
				dice.bottom = left;				
			} else if(k == 3) { //북
				nr += dr[2]; nc += dc[2];
				if(nr < 0 || nr >=N || nc < 0 || nc>=M) {
					nr = r; nc = c; //nr, nc 초기화하고
					continue;
				}
				dice.top = front;
				dice.front = bottom;
				dice.bottom = back;
				dice.back = top;				
			} else if(k == 4) { //남
				nr += dr[3];	nc += dc[3];
				if(nr < 0 || nr >=N || nc < 0 || nc>=M) {
					nr = r; nc = c; //nr, nc 초기화하고
					continue;
				}
				dice.top = back;
				dice.back = bottom;
				dice.bottom = front;
				dice.front = top;
			}
			
			if(map[nr][nc] == 0) {
				map[nr][nc] = dice.bottom;
			} else {
				dice.bottom = map[nr][nc];
				map[nr][nc] = 0;
			}
			System.out.println(dice.top);
		}
	}
}