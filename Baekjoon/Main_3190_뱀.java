package Aug9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_3190_뱀 {
	static int N, K;
	static int[][] map;
	static int[][] snake; //뱀의 현재 모양/위치 저장
	static int dr[] = {-1, 0, 1, 0}; //위, 오른쪽, 아래, 왼쪽
	static int dc[] = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int K = Integer.parseInt(bf.readLine()); //사과의 개수
		map = new int[N][N];
		snake = new int[N][N];
		
		//0,0에는 사과가 없다.
		for(int k = 0; k<K; k++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = 1; //문제에서 좌표는 1,1부터 시작		
		}
		int L = Integer.parseInt(bf.readLine());
		
		List<Integer> listA = new ArrayList<>();
		List<Character> listB = new ArrayList<>();
		
		//이동정보-시작은 오른쪽방향(index = 1)
		int index = 1;
		for(int l = 0; l<L; l++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			listA.add(Integer.parseInt(st.nextToken())); //시간 정보
			listB.add(st.nextToken().charAt(0)); //방향정보
		}		
		
		Queue<Pair> queue = new LinkedList<Pair>(); 
		queue.add(new Pair(0, 0));
		snake[0][0] = 1;
		
		int i = 0, cnt = 0;
		int nr = 0, nc = 0;
		int a = 0, b = 0;
		top:
		while(true) {
			if(i < listA.size()) {
				a = listA.get(i);
				b = listB.get(i++);
			}
			
			while(true) {
				nr += dr[index];
				nc += dc[index]; //한칸 이동
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || snake[nr][nc] == 1) {
					//벽에 부딪히거나 자기자신의 몸과 부딪힐 경우 종료
					cnt++;
					break top;
				}
				queue.add(new Pair(nr, nc)); //뱀 머리이동
				snake[nr][nc] = 1;
				
				if(map[nr][nc] != 1) { //사과가 없을 경우 뱀 꼬리 이동, 길이 그대로
					Pair pair = queue.poll();
					int x = pair.x;
					int y = pair.y;
					snake[x][y] = 0;
				} else {
					map[nr][nc] = 0; //사과 먹기
				}				
				
				if(++cnt == a) {
					switch(b) {
					case 'L':
						index = (index + 3) % 4; break;
					case 'D':
						index = (index + 1) % 4; break;
					}
					break;
				}
			}	
			
		}
		System.out.println(cnt);
	}

}
