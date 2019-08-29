import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class Solution_D4_3124_최소스패닝트리 {
	
	static class Pair {
		int x, y, w;
		public Pair(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w; //weight
		}
	}
	
    private static int[] parents;
    static int V, E;
 
    public static void main(String[] args) throws Exception {
        StringTokenizer st = null;
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int t = 1; t <= T; t++) {
        	V = sc.nextInt();
        	E = sc.nextInt();
			parents = new int[V + 1];
			make();
			Pair[] edge = new Pair[E];
			for (int i = 0; i < E; i++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int c = sc.nextInt();

				edge[i] = new Pair(s, e, c);
			}
			Arrays.sort(edge, new Comparator<Pair>() {
				public int compare(Pair e1, Pair e2) {
					return e1.w - e2.w;
				}
			});
			long ans = 0L;
//			int count = 0;
			for (int i = 0; i < E; i++) {
				
				if(union(edge[i].x, edge[i].y)) { //union이 성공할 경우
					ans += edge[i].w;
//					count++; 
				}
//				if(count==V-1) break; //있으면 시간단축
			}
			System.out.println("#"+t+" "+ans);
		}
    }
    
    static void make() { //make set : 모든 원소를 개별적인 집합으로 생성
		Arrays.fill(parents,  -1);
	}
	static int find(int a) {
		if(parents[a] < 0) return a; //자신이 루트이면 자신 리턴
		return parents[a] = find(parents[a]); //대입하고 리턴 한 번에 가능
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
 
}
