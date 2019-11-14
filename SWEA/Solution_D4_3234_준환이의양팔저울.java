import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3234_준환이의양팔저울 {
	public static int N, totalSum, result, cnt;
	public static int[] w;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			N = Integer.parseInt(bf.readLine());//1<=N<=9
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			w = new int[N];
			for(int i = 0; i<N; i++){
				w[i] = Integer.parseInt(st.nextToken()); //1<=w[i]<=999
			}
			
			cnt = 0; //가능한 가지수
			
			perm(0, 0, 0);
			
			System.out.println("#"+test_case+" "+cnt);
		}
		
	}
	
	/** step: 현재단계, l:현재까지 저울의 왼쪽 추들의 합, r:현재까지 저울의 오른쪽 추들의 합 */
	public static void perm(int step, int l, int r) {
		if(w.length==step) {
			//종료파트
			cnt++;
		} else {
			for(int i = step; i<w.length; i++) {
				//1. step <-> i swap
				int temp = w[step];
				w[step] = w[i];
				w[i] = temp;
				
				//2. 다음단계 재귀호출
				perm(step+1, l+w[step], r); //왼쪽에 w[step]추를 올려놓고 재귀호출
				
				if(l >= r+w[step]) //가지치기
					perm(step+1, l, r+w[step]); //오른쪽에 w[step]추를 올려놓고 재귀호출
				
				//3. step <-> i swap
				temp = w[step];
				w[step] = w[i];
				w[i] = temp;
			}
		}
	}
	
}

