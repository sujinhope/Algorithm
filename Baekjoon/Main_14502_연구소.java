package September.Sep04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14502_������ {
	//flag ����
	static int N, M;
	static int map[][];
	static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, 1, -1};
	
	//static int selected[] = new int[3]; //���õ� �� ���� �� ��ġ
	static int arr[]; //N*M (ĭ�� ����)
	static boolean visited[];
	
	//current. => �ð� ��� ��
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arr = new int[N*M];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = i;
		}
		
		for(int i = 0; i<N; i++){
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		visited = new boolean[N*M];
		
		perm(list);
		
		System.out.println(max);		
	}
	
	/* �̷��� �ϴ� ���� �����ϱ�
	private static void perm(int step) {
		if(!finish) return;
		if(step==3) {
			flag = false;
			spread(); 
			if(!flag) finish = false;
			return;
		}
		
		for(int i = step; i<arr.length; i++) {
			
		}
		
	}
	*/
	private static int max = -1;
	
	private static void perm(List<Integer> list) {
		
		if(list.size()==3) { //���õ� ��ġ�� �� �����
			for(int i = 0; i<list.size(); i++) {
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				
				if(map[r][c] != 0) 
					return; //���� �� ����� ���� Ȯ�ꤤ��
			}
			for(int i = 0; i<list.size(); i++) {
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				map[r][c] = 1; //1�� ��
			}
			
			spread();
			
			for(int i = 0; i<list.size(); i++) { //�� ���󺹱�
				int r = list.get(i)/M;
				int c = list.get(i)%M;
				map[r][c] = 0;
			}
			return;
		}
		
		for(int i = 0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				perm(list);
				list.remove(list.size()-1);
				visited[i] = false;				
			}
		}
		
	}
	
	private static void spread() {
		
		int newMap[][][] = new int[2][N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				newMap[1][i][j] = newMap[0][i][j] = map[i][j];
			}
		}
		
		int idx = 0;
		
		while(true) {
			// Ȯ�� �� ��
			boolean flag = false;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(newMap[(idx+1)%2][i][j]==2) {
						for(int k = 0; k<4; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if(nr>=0 && nr<N && nc>=0 && nc<M
									&& newMap[(idx+1)%2][nr][nc]==0) {
								newMap[idx%2][nr][nc] = 2;
								flag = true;
							}
						}
					}
				}
			}
			
			//Ȯ�� �ϰ��� �ٽ� ����
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					newMap[(idx+1)%2][i][j] = newMap[idx%2][i][j];
				}
			}
			
			if(flag == false) break;
			
			idx++;
		}
		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				//idx��� ���� ������ ������ �Ѵ� �Ȱ��� ���������Ƿ�
				if(newMap[idx%2][i][j]==0) sum++;
//				sum += newMap[idx%2][i][j];
			}
		}
		
		if(sum > max) 
			max = sum;
		
	}

}
