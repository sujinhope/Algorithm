package GitUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class Main_15686_치킨배달 {
	public static int N, M, min = 100000001, visited[];
	public static List<Pair> chickens = new ArrayList<Pair>();
	public static List<Pair> home = new ArrayList<Pair>();
	
	public static void calDistance(List<Pair> chicken) {//치킨거리 계산하는 메서드
		int sum = 0; //도시의 치킨거리
		
		for(int i = 0; i<home.size(); i++) {
			int minDist = 100000000; //한 집에서의 치킨거리
			for(int j = 0; j<chicken.size(); j++) {
				int cX = chicken.get(j).x, cY = chicken.get(j).y;
				int hX = home.get(i).x, hY = home.get(i).y; //j아님 i주의
				int d = Math.abs(cX-hX) + Math.abs(cY-hY); //치킨거리
				if(d<minDist) minDist = d;
			}
			sum += minDist;
		}
		if(sum < min)
			min = sum;
	}
	
	public static void go(int here, List<Pair> chicken) { //M개의 치킨집리스트 추출
		if(chicken.size() >= M) {
			//함수 호출
			calDistance(chicken);
			return;
		}
		
		for(int i = here; i<chickens.size(); i++) {
			if(visited[i] != 1) { //방문한적이 없으면
				visited[i] = 1;
				chicken.add(new Pair(chickens.get(i).x, chickens.get(i).y));
				go(i + 1, chicken);
				chicken.remove(chicken.size() - 1);
				visited[i] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int a = 0;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				a = Integer.parseInt(st.nextToken());
				if(a == 1)
					home.add(new Pair(i, j));
				else if(a == 2)
					chickens.add(new Pair(i, j));				
			}
		}
		visited = new int[chickens.size()];
		
		List<Pair> list = new ArrayList<Pair>();
		go(0, list);
		System.out.println(min);
	}
    
}