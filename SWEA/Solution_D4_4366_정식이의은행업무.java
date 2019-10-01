import java.util.Scanner;

public class Solution_D4_4366_정식이의은행업무 {
	public static String bi, tri;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			bi = sc.nextLine();
			tri = sc.nextLine();
			
			StringBuilder bitemp = new StringBuilder(bi);
			for(int i = 0; i<bi.length(); i++) {
				bitemp = new StringBuilder(bi);
				if(bitemp.charAt(i) == '0')
					bitemp.setCharAt(i, '1');
				else
					bitemp.setCharAt(i, '0');
				int biDec = Integer.parseInt(bitemp.toString(), 2);
				String bi2tri = Integer.toString(biDec, 3);
				
				int cnt = 0;
				if(tri.length() > bi2tri.length()) {
					int len = tri.length()-bi2tri.length();
					for(int j = 0; j<len; j++) {
						bi2tri = '0'+bi2tri;
					}
				} else {
					for(int j = 0; j<bi2tri.length()-tri.length(); j++) {
						tri = '0'+tri;
					}
				}
				for(int j = 0; j<tri.length(); j++) {
					if(bi2tri.charAt(j) != tri.charAt(j)) cnt++;
				}
				if(cnt==1) {
					System.out.println("#"+test_case+" "+biDec); break;
				}
			}
		}
	
	}

}
