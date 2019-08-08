package HW_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_D3_1228_암호문1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(bf.readLine()); //암호 길이
			//원래 암호
			String[] code = bf.readLine().split(" ");
			List<String> list = new ArrayList<String>(Arrays.asList(code));
			
			int K = Integer.parseInt(bf.readLine()); //명령어 개수
			
			String[] str = bf.readLine().split(" "); //명령어
			int index = 0;
			for(int i = 0; i<K; i++) {
				int cnt = 1;
				int pos = Integer.parseInt(str[index + 1]); //수정위치 x : x뒤에 삽입
				int num = Integer.parseInt(str[index + 2]); //y
				for(int j = pos; j<pos + num; j++) {
					if(j >= N) break;
					list.add(j, str[index+2+cnt++]);
				}
				index += num + 3;
			}
			
			System.out.print("#"+t+" ");
			for(int i = 0; i<10; i++) {
				System.out.print(list.get(i)+ " ");
			}
			
			System.out.println();
		}
		
		
		
	}

}
