import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3234_��ȯ���Ǿ������� {
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
			
			cnt = 0; //������ ������
			
			perm(0, 0, 0);
			
			System.out.println("#"+test_case+" "+cnt);
		}
		
	}
	
	/** step: ����ܰ�, l:������� ������ ���� �ߵ��� ��, r:������� ������ ������ �ߵ��� �� */
	public static void perm(int step, int l, int r) {
		if(w.length==step) {
			//������Ʈ
			cnt++;
		} else {
			for(int i = step; i<w.length; i++) {
				//1. step <-> i swap
				int temp = w[step];
				w[step] = w[i];
				w[i] = temp;
				
				//2. �����ܰ� ���ȣ��
				perm(step+1, l+w[step], r); //���ʿ� w[step]�߸� �÷����� ���ȣ��
				
				if(l >= r+w[step]) //����ġ��
					perm(step+1, l, r+w[step]); //�����ʿ� w[step]�߸� �÷����� ���ȣ��
				
				//3. step <-> i swap
				temp = w[step];
				w[step] = w[i];
				w[i] = temp;
			}
		}
	}
	
}

