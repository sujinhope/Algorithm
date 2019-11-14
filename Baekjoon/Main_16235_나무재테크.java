package Oct17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16235_나무재테크 {
	public static int N, M, K;
	public static int nutrient[][], map[][];
	public static List<Tree>[][] trees;
	public static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0},
						dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static class Tree {
		int age;
		boolean die;
		public Tree(int age, boolean die) {
			this.age = age;
			this.die = die;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nutrient = new int[N+2][N+2];
		map = new int[N+2][N+2];
		//한 자리에 나무가 여러개일 수도 있음.
//		List<Tree> trees = new ArrayList<>();
		trees = new ArrayList[N+2][N+2];
		for(int i = 0; i<N+2; i++) {
			for(int j = 0; j<N+2; j++) {
				trees[i][j] = new ArrayList<Tree>();
			}
		}
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 1; j<=N; j++) {
				nutrient[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			trees[r][c].add(new Tree(age, false));
		}
		
		for(int k = 0; k<K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		//남아있는 나무의 개수
		int sum = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				sum += trees[i][j].size();
			}
		}	
		System.out.println(sum);
	}

	private static void spring() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(trees[i][j].size()>0) {
					Collections.sort(trees[i][j], new Comparator<Tree>() {
						@Override
						public int compare(Tree o1, Tree o2) {
							return o1.age - o2.age;
						}
					});
					for(int k = 0; k<trees[i][j].size(); k++) {
						int age = trees[i][j].get(k).age;
						if(age <= map[i][j]) {
							map[i][j] -= age;
							trees[i][j].get(k).age++;
						} else { //양분이 부족하면 죽음
							trees[i][j].get(k).die = true;
						}
					}
				}
			}
		}
	}

	private static void summer() {
		// 양분 추가, 죽은 나무 제거
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(trees[i][j].size()==0) continue;
				
				//맨 뒤 인덱스부터 탐색 후 제거
				for(int k = trees[i][j].size()-1; k>=0; k--) {
					boolean die = trees[i][j].get(k).die;
					if(die) {
						map[i][j] += (trees[i][j].get(k).age/2);
						trees[i][j].remove(k);
					}
				}
			}
		}
	}

	private static void autumn() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(trees[i][j].size()==0) continue;
				
				for(int k = trees[i][j].size()-1; k>=0; k--) {
					int age = trees[i][j].get(k).age;
					if(age % 5 == 0) {
						for(int p = 0; p<8; p++) {
							int nr = i + dr[p];
							int nc = j + dc[p];
							if(nr>0 && nr<=N && nc>0 && nc<=N) {
								trees[nr][nc].add(new Tree(1, false));
							}
						}
					}
				}
			}
		}
	}

	private static void winter() {
		//양분 추가 - nutrient[i][j]만큼
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				map[i][j] += nutrient[i][j];
			}
		}
	}

}
