package ss;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main_2577_회전초밥 {

	public static int N, d, k, c;
	public static int sushi[];
	public static int chk[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		chk = new int[d+1];
		
		for(int i = 0; i<N; i++) {
			sushi[i] = Integer.parseInt(bf.readLine());
		}
		
		List<Integer> list = new ArrayList<Integer>();
		
		int cnt = 0;
		for(int i = 0; i<k; i++) {
			list.add(sushi[i]);
			if(chk[sushi[i]]==0) {
				cnt++;
			}
			chk[sushi[i]]++;
		}
		if(chk[c]==0) {
			cnt++;
		}
		chk[c]++;
		int answer = cnt;
		
		int first = list.get(0), next = 0;
		for(int index = k; index<N+k; index++) {
			int i = index%N;
			if(chk[first]==1) {
				cnt--;
			}
			list.remove(0);
			chk[first]--;
			
			first = list.get(0);
			next = sushi[i];
			list.add(next);
			if(chk[next]==0) {
				cnt++;
			}
			chk[next]++;
			answer = Math.max(answer, cnt);
			if(answer==k+1) break;
		}
				
		System.out.println(answer);
		
	}

}
