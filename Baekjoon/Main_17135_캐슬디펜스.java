import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
	
	static int N, M, D, max = -1;
	static int map[][];
	//List<Integer> archers; //궁수들의 행은 언제나 서로 같음. 열의 위치만 따로 저장
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //행
		M = Integer.parseInt(st.nextToken()); //열
		D = Integer.parseInt(st.nextToken()); //공격거리 제한
		
		map = new int[N][M];
		visited = new boolean[M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		makeList(list);
		
		System.out.println(max);
	}
	
	public static boolean visited[];
	
	public static void makeList(List<Integer> archers) {
		if(archers.size() == 3) {
			simulation(archers);
			return;
		}
		
		for(int i = 0; i<M; i++) {
			if(!visited[i]) {
				archers.add(i);
				visited[i] = true;
				makeList(archers);
				archers.remove(archers.size()-1);
				visited[i] = false;
			}
		}
	}

	private static void simulation(List<Integer> archers) {
		
		int newMap[][] = new int[N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		int count = 0;
		
		for(int arcR = N; arcR>0; arcR--) { //적의 위치는 그대로. 궁수가 한 칸씩 위로 올라가면서 진행.
			int pos[][] = new int[3][2]; //i번째 궁수가 제거할 적의 위치 = pos[i][0], pos[i][1]
			
			for(int i = 0; i<3; i++) { //archers.size()==3
				int arcC = archers.get(i);
				int min = 1000;
				
				pos[i][1] = 20;
				
				for(int r = arcR-1; r>=0; r--) { //현재 보고 있는 적의 행 위치(궁수 바로 위의 행부터 0행까지 탐색)
					if(arcR - r > D) break; //수직거리가 D를 넘을 경우
					
					for(int c = 0; c<M; c++) { //현재 보고 있는 적의 열 위치
						if(newMap[r][c] == 1) {
							int dist = Math.abs(arcR-r) + Math.abs(arcC-c);
							if(dist <= D && dist < min) {
								min = dist;
								pos[i][0] = r; pos[i][1] = c;
							} else if(dist <= D && dist == min && c < pos[i][1]) {
								pos[i][0] = r; pos[i][1] = c;
							}
//							if(dist <= D && dist <= min && c < pos[i][1]) { //더 왼쪽 위치 택함.
//								//pos[i][1] = 제거할 적의 열 위치
//								min = dist;
//								pos[i][0] = r; pos[i][1] = c;
//							}
						}
					}
				}
			}
			
			for(int i = 0; i<3; i++) {
				int delR = pos[i][0], delC = pos[i][1]; //i번째 궁수가 제거할 적의 위치
				if(delC == 20) continue;
				if(newMap[delR][delC] != 0) {
					newMap[delR][delC] = 0;
					count++;
				}
			}
		}
		
		if(count > max) 
			max = count;
	}

}
