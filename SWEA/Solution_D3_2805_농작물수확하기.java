package Aug9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_2805_농작물수확하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(bf.readLine());
			
			String[] s = new String[N];
			for(int i = 0; i<N; i++) {
				s[i] = bf.readLine();			
			}
			
			int index = N / 2;
			int sum = 0;
			for(int i = 0; i<N; i++) {
				if(i <= N/2) {
					for(int j = -i; j<=i; j++) {
						sum += s[i].charAt(j + index) - 48;
					}
				} else {
					for(int j = -(N - i - 1); j<=(N - i - 1); j++) {
						sum += s[i].charAt(j + index) - 48;
					}
				}
			}
			
			System.out.println("#"+t+" "+sum);
			
		}
	}

}
