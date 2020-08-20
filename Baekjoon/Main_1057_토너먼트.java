package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N, A, B;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken()) - 1;
		B = Integer.parseInt(st.nextToken()) - 1;
		
		int repeat = 0;
		
		while (A != B) {
//			System.out.println(A + " " + B);
			A /= 2;
			B /= 2;
			repeat++;
		}
//		System.out.println(A + " " + B);
		
		System.out.println(repeat);
	}

}
