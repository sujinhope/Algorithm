import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main_1197_최소스패닝트리 {
	
	static class Pair {
		int x, y, w;
		public Pair(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w; //weight
		}
	}
	
    private static int[] parent;
    static int V, E;
 
    public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(bf.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];

		for (int i = 1; i <= V; i++) parent[i] = i;

		Pair[] edge = new Pair[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine(), " ");

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edge[i] = new Pair(s, e, c);
		}
		Arrays.sort(edge, new Comparator<Pair>() {
			public int compare(Pair e1, Pair e2) {
				return e1.w - e2.w;
			}
		});

		int ans = 0;
		for (int i = 0; i < E; i++) {
			int p = find(edge[i].x);
			int q = find(edge[i].y);

			if (p == q) continue; // 같은 그룹일 경우

			parent[q] = p;
			ans += edge[i].w;
		}
		System.out.println(ans);
    }
 
    private static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }
 
}
