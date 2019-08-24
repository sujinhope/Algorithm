import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466_텀프로젝트 {
	static int N, cycle[], num, count = 0;
	static boolean chk[];
	static int student[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int t = 1; t<=T; t++) {
			num = 0;
			count = 0;
			N = Integer.parseInt(bf.readLine());
			student = new int[N+1];
			chk = new boolean[N+1];
			cycle = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for(int i = 1; i<=N; i++) {
				student[i]=Integer.parseInt(st.nextToken());				
			}
			for(num = 1; num<=N; num++) {
				if(!chk[num]) {
					dfs(num); //i = 싸이클번호;
				}
			}
			
			System.out.println(N - count);
		}
	}
	
	private static void dfs(int now) {
		cycle[now] = num;
		chk[now] = true;
		int next = student[now];
		if (chk[next] && cycle[now] == cycle[next]) { 
			count++;
			for(int i = next; i != now; i = student[i]) count++;
		} else if(!chk[next]) dfs(next);	
	}

}
