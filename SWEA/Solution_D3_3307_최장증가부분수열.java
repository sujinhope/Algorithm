import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_3307_최장증가부분수열 {
	
	static int[] num;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = sc.nextInt();
			num = new int[N];
			int LIS[] = new int[N];
			
			for(int index = 0; index<N; index++) {
				num[index] = sc.nextInt();
				LIS[index] = 1;
			}
			
			LIS[0] = 1;
			for(int i = 1; i<N; i++) {
				for(int j = 0; j<i; j++) {
					if(num[j] < num[i] && LIS[i] < LIS[j]+1)
						LIS[i] = LIS[j]+1;
				}
			}
			Arrays.sort(LIS);
			System.out.println("#"+test_case+" "+LIS[N-1]);
		}
	}

}
