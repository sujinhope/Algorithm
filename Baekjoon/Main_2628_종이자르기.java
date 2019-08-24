import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		// ctrl+1 Exception
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(bf.readLine());
		ArrayList<Integer> cutR = new ArrayList<>();
		ArrayList<Integer> cutC = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " " );
			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if(type == 0) {
				cutR.add(num);
			} else {
				cutC.add(num);
			}
		}
		
		cutR.add(0); //영역의 시작점 추가, 자를 떄 기준이 될 수 있도록
		cutR.add(r);
		cutC.add(0);
		cutC.add(c);
		
		Collections.sort(cutR);
		Collections.sort(cutC);
		
		int maxR = 0; //최대 세로 높이 저장
		for(int i = 1; i<cutR.size(); i++) {
			if(maxR < cutR.get(i)-cutR.get(i-1)) {
				maxR = cutR.get(i)-cutR.get(i-1);
			}
		}
		int maxC = 0; //최대 세로 높이 저장
		for(int i = 1; i<cutC.size(); i++) {
			if(maxC < cutC.get(i)-cutC.get(i-1)) {
				maxC = cutC.get(i)-cutC.get(i-1);
			}
		}
		System.out.println(maxR*maxC);
	}

}

