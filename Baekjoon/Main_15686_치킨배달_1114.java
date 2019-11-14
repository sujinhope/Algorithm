import java.util.*;
import java.io.*;

public class Main_15686_치킨배달_1114 {
	
	private static int N, M, answer = Integer.MAX_VALUE, csize, hsize;
	private static int map[][], distances[][];
	private static List<Pair> chickensPos = new ArrayList<>();
	private static List<Pair> homesPos = new ArrayList<>();
	
	
	public static class Pair {
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					chickensPos.add(new Pair(i, j));
				} else if(map[i][j]==1) {
					homesPos.add(new Pair(i, j));
				}
			}
		}
		
		csize = chickensPos.size();
		hsize = homesPos.size();
		distances = new int[hsize][csize];
		for(int i= 0; i<hsize; i++) { //행이 일반 집
			Pair home = homesPos.get(i);
			for(int j = 0; j<csize; j++) { //열이 치킨집
				Pair chicken = chickensPos.get(j);
				distances[i][j] = Math.abs(chicken.r-home.r)+ Math.abs(chicken.c-home.c); 
			}
		}
		
		comb(0, new ArrayList<Integer>());
		
		System.out.println(answer);
	}
	
	private static void comb(int here, List<Integer> chickens) {
		if(chickens.size()==M) {
			calculation(chickens);
			return;
		}
		for(int i = here; i<csize; i++) {
			chickens.add(i); //치킨집 번호를 넣는다.
			comb(i+1, chickens);
			chickens.remove(chickens.size()-1);
		}
	}

	private static void calculation(List<Integer> chickens) {
		
		int length = chickens.size();
		int sum = 0;
		
		for(int i = 0; i<hsize; i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j<length; j++) {
				int idx = chickens.get(j);
				if(distances[i][idx]<min)
					min = distances[i][idx];
			}
			sum += min;
		}
		
		answer = Math.min(answer, sum);		
	}
	

}
