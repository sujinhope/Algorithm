package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class algo0818_1766 {

	static int N, M;
	static int in[];
	static List<Integer> edge[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		in = new int[N+1];
		edge = new List[N+1];
		for(int i = 0; i<N+1; i++) {
			edge[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
//			System.out.println((i+1)+". "+before+" " + next);
			edge[before].add(next);
			in[next]++;
		}
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 1; i<=N; i++) {
			if(in[i] == 0) {
				pq.add(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			
			System.out.print(now+" " );
			
			for (Integer next : edge[now]) {
				in[next]--;
				if(in[next]==0)
					pq.add(next);
			}
		}
		
	}

}
