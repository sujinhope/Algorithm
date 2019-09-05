package September.Sep04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D4_1258_행렬찾기_박수진 {
	
	static int T, N;
	static int map[][];
	static boolean visited[][];
	static List<Matrix> ans;
	
	static class Matrix {
		int r, c, size;
		public Matrix(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(bf.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(bf.readLine());
			ans = new ArrayList<Matrix>();
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] !=0 && !visited[i][j]) {
						int endR = i+1, endC = j+1;
						while(endC<N && map[i][endC]!=0) {
							endC++;
						}
						while(endR<N && map[endR][j]!=0) {
							endR++;
						}
						for(int r = i; r<endR; r++) {
							for(int c = j; c<endC; c++) {
								visited[r][c] = true;
							}
						}
						count++;
						ans.add(new Matrix(endR-i, endC-j, (endR-i)*(endC-j)));
					}
				}
			}
			
			Collections.sort(ans, new Comparator<Matrix>() {
				public int compare(Matrix o1, Matrix o2) {
					int result = o1.size-o2.size;
					
					return result!=0 ? result : o1.r-o2.r;
				}				
			});
			
			System.out.print("#"+t+" "+count);
			for(int i = 0; i<ans.size(); i++) {
				System.out.print(" "+ans.get(i).r+" "+ans.get(i).c);
			}
			System.out.println();
		}
		
	}

}