package Aug9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13458_시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int A = Integer.parseInt(bf.readLine());
		List<Integer> list = new ArrayList<Integer>();
		
		//응시생 입력
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i<A; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(bf.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long result = 0l;
		for(int i = 0; i<A; i++) {
			int num = list.get(i) - B;
			result++; //주감독관
			if(num < 0) continue;
			if(num % C == 0) {
				result += num/C;
			} else
				result += num/C + 1;
		}
		
		System.out.println(result);
	}

}
