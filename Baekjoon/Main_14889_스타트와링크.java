package GitUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
	static int N, min = 1000000000, total_sum = 0;
	static int S[][];
	static boolean visited[];
	
	public static void go(int here, int cnt) {
		if(cnt == N/2) {
			min = Math.min(min, cal());
			return;
		}
		for(int i = here; i<N; i++) {
			visited[i] = true;
			go(i + 1, cnt + 1);
			visited[i] = false;
		}
	}
	
	public static int cal() {
		int sumA = 0, sumB = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = i + 1; j<N; j++) {
				if(visited[i] == true && visited[j] == true) {
					sumA += S[i][j] + S[j][i];
				} else if(visited[i] == false && visited[j] == false) {
					sumB += S[i][j] + S[j][i];
				}
			}
		}
		return Math.abs(sumA - sumB);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		S = new int[N][N];
		visited = new boolean[N];
		
		StringTokenizer st;		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
				total_sum += S[i][j];
			}
		}
		go(0, 0);
		System.out.println(min);
	}
}