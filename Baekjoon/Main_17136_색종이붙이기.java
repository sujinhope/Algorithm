import java.util.Scanner;

public class Main_17136_색종이붙이기 {
	static int N = 10;
	static int map[][] = new int[N][N];
	static int total = 0;
	static int visited[][] = new int[N][N];
	static int min = Integer.MAX_VALUE;
	static int paper[] = {0, 5, 5, 5, 5, 5}; //1~5 크기별로 5장씩 초기화
	static boolean flag = false;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) total++;
			}
		}
		
		go(0, 0, 0);
		
		System.out.println(flag ? min : -1);
	}

	private static void go(int r, int c, int count) {
		if(count >= min) return;

		//다 붙였는지 체크
		if(total == 0) {
			if(count < min) min = count;
			flag = true;
			return;
		}
		
		//종이 개수 초과하면 return;
		int remain = 0;
		for(int i = 1; i<=5; i++) {
			remain += paper[i];
		}
		if(remain == 0) return;
		
		
		//붙일 수 있는지 체크
		int i = 0, j = 0;
		top:for (i = 0; i < N; i++) {
			for(j = 0; j< N; j++) {
				if (map[i][j] == 1 && visited[i][j]!=1) { 
					break top;
				}
			}
		}
		
		for (int n = 5; n >= 1; n--) {
			//색종이 개수 체크
			if (!check(i, j, n)) continue;
			
			if(paper[n]==0) continue; //n크기의 종이가 안남아있으면 패스
			
			total -= n*n;
			paper[n]--;
			fill(i, j, n, 1); //visited 채우기
			go(i, j, count+1);
			paper[n]++;
			fill(i, j, n, 0);
			total += n*n;
		}
		
	}
	
	//n크기의 종이를 k==1일 경우 붙이고, k==0일 경우 떼기
	private static void fill(int r, int c, int n, int k) {
		for(int i = r; i<r+n; i++) {
			for(int j = c; j<c+n; j++) {
				visited[i][j] = k;
			}
		}
	}
	
	//n크기의 종이를 붙일 수 있는지 체크
	private static boolean check(int r, int c, int n) {
		for(int i = r; i<r+n; i++) {
			if(i>=N) return false;
			for(int j = c; j<c+n; j++) {
				if(j>=N || map[i][j]==0 || visited[i][j]==1) return false;
			}
		}		
		return true;
	}

}
