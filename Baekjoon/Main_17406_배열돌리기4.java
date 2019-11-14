import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기4 {
	
	static int N, M, K, map[][];
	static int rotate[][];
	static int A[][], Acopy[][];
	
	public static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		rotate = new int[K][3];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//순열
		arr = new int[3]; //여기 
		for(int i = 0; i<3; i++) {
			
		}
		
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			rotate[i][0] = Integer.parseInt(st.nextToken());
			rotate[i][1] = Integer.parseInt(st.nextToken());
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[K];
		List<Integer> index = new ArrayList<Integer>();
		perm(index);
		
		System.out.println(min);
		

	}
	
	static boolean visited[];
	public static void perm(List<Integer> index) {
		if(index.size()==K) {
			rotateFunc(index);
			//최소값 갱신 - 이건 rotate에서
			return;
		}
		
		for(int i = 0; i<K; i++) {
			if(!visited[i]) {
				index.add(i);
				visited[i] = true;
				perm(index);
				index.remove(index.size()-1);
				visited[i] = false;
			}
		}
	}	
	
	static int min = Integer.MAX_VALUE;
	
	public static void rotateFunc(List<Integer> indexes) {
		
		//newMap복사
		int newMap[][] = new int[N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		for(int k = 0; k<K; k++) {
			int r = rotate[indexes.get(k)][0], c = rotate[indexes.get(k)][1], s = rotate[indexes.get(k)][2];
			
			//회전
			//왼위-오위-오아-왼아 : (r-s,c-s),(r-s,c+s),(r+s,c+s),(r+s,c-s)
			for(int i = s; i>=0; i--) {
				int minR, maxR, minC, maxC;
				minR = r-i-1;
				maxR = r+i-1;
				minC = c-i-1;
				maxC = c+i-1;
				
				int before = newMap[minR][minC], temp;
				for(int a = minC + 1; a<=maxC; a++) { //여기서는 첫 번째 값은 넣을게 아직 없으므로 pass
					temp = newMap[minR][a];
					newMap[minR][a] = before;
					before = temp;
				}
				
				for(int a = minR + 1; a<=maxR; a++) { //모서리는 겹침
					temp = newMap[a][maxC];
					newMap[a][maxC] = before;
					before = temp;
				}
				
				for(int a = maxC - 1; a>=minC; a--) {
					temp = newMap[maxR][a];
					newMap[maxR][a] = before;
					before = temp;
				}
				
				for(int a = maxR - 1; a>=minR; a--) {
					temp = newMap[a][minC];
					newMap[a][minC] = before;
					before = temp;
				}			
			}
		}
		
		//최소값 계산
		for(int i = 0; i<N; i++) {
			int sum = 0;
			for(int j = 0; j<M; j++) {
				sum += newMap[i][j];
			}
			if(sum < min) min = sum;
		}
				
	}	
	
}
