import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_1873_상호의배틀필드 {

	static int T, H, W, N;
	static char map[][];
	static int trDir, trR, trC;
	static String str;
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
	static Map<Character, Integer> direction = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		
		direction.put('U',0);
		direction.put('D',1);
		direction.put('L',2);
		direction.put('R',3);
		
		for(int t = 1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			for(int i = 0; i<H; i++) {
				String s = bf.readLine();
				for(int j = 0; j<W; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j]=='<' || map[i][j]=='>' || map[i][j]=='^'||map[i][j]=='v') {
						trR = i; trC = j;
						switch(map[i][j]) {
						case '^': trDir = 0; break;
						case 'v': trDir = 1; break;
						case '<': trDir = 2; break;
						case '>': trDir = 3; break;
						}
					}
				}
			}
//			bf.readLine();
			N = Integer.parseInt(bf.readLine());
			str = bf.readLine();
			
			simulation();
			
			System.out.print("#"+ t + " ");
			for(int i = 0; i<H; i++) {
				for(int j = 0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
		
	}

	private static void simulation() {
		int nr, nc;
		
		for(int i = 0; i<N; i++) {
			char c = str.charAt(i);
			
			if(c=='S') { //포탄 발사
				int cnt = 1;
				while(true) {
					nr = trR + dr[trDir]*cnt;
					nc = trC + dc[trDir]*cnt;
					cnt++;
					if(nr<0 || nr>=H || nc<0 || nc>=W || map[nr][nc]=='#') break;
					if(map[nr][nc]=='*') {
						map[nr][nc]='.'; break;
					}					
				}			
				
			} else {
				trDir = direction.get(c); //전차의 방향
				nr = trR + dr[trDir];
				nc = trC + dc[trDir];
				//평지이면 한 칸 이동 - 전차는 맵 밖을 벗어나지 않음
				if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]=='.') {
					map[trR][trC] = '.';
					trR = nr;
					trC = nc; //물은 못지나감
				}				
				switch(trDir) {
				case 0: map[trR][trC] = '^'; break;
				case 1: map[trR][trC] = 'v'; break;
				case 2: map[trR][trC] = '<'; break;
				case 3: map[trR][trC] = '>'; break;
				}
			}	
			
		}	
		
	}

}
