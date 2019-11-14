import java.util.Scanner;

public class Main_17136_�����̺��̱� {
	static int N = 10;
	static int map[][] = new int[N][N];
	static int total = 0;
	static int visited[][] = new int[N][N];
	static int min = Integer.MAX_VALUE;
	static int paper[] = {0, 5, 5, 5, 5, 5}; //1~5 ũ�⺰�� 5�徿 �ʱ�ȭ
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

		//�� �ٿ����� üũ
		if(total == 0) {
			if(count < min) min = count;
			flag = true;
			return;
		}
		
		//���� ���� �ʰ��ϸ� return;
		int remain = 0;
		for(int i = 1; i<=5; i++) {
			remain += paper[i];
		}
		if(remain == 0) return;
		
		
		//���� �� �ִ��� üũ
		int i = 0, j = 0;
		top:for (i = 0; i < N; i++) {
			for(j = 0; j< N; j++) {
				if (map[i][j] == 1 && visited[i][j]!=1) { 
					break top;
				}
			}
		}
		
		for (int n = 5; n >= 1; n--) {
			//������ ���� üũ
			if (!check(i, j, n)) continue;
			
			if(paper[n]==0) continue; //nũ���� ���̰� �ȳ��������� �н�
			
			total -= n*n;
			paper[n]--;
			fill(i, j, n, 1); //visited ä���
			go(i, j, count+1);
			paper[n]++;
			fill(i, j, n, 0);
			total += n*n;
		}
		
	}
	
	//nũ���� ���̸� k==1�� ��� ���̰�, k==0�� ��� ����
	private static void fill(int r, int c, int n, int k) {
		for(int i = r; i<r+n; i++) {
			for(int j = c; j<c+n; j++) {
				visited[i][j] = k;
			}
		}
	}
	
	//nũ���� ���̸� ���� �� �ִ��� üũ
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
