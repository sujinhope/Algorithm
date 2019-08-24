import java.util.Scanner;
 
public class Solution_D3_NQueen {
	static int N, T, count, check[];
	
    public static void queenCount(int deep){
        if(deep == N){
            count++;
            return;
        }
        for(int i = 0; i < N; i++){
            if(check(deep, i)){
                check[deep] = i;
                queenCount(deep+1);
            }
        }
        check[deep] = 0;
        return;
    }
    public static boolean check(int deep, int target){
        for(int i = 0 ; i < deep; i++)
            if(check[i] == target || check[i] + (deep-i) == target ||  
                check[i] - (deep-i) == target)
                return false;
        return true;
    }
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	T = sc.nextInt();
    	
    	for(int t = 1; t<=T; t++) {
    		N = sc.nextInt();
    		check = new int[N];
    		count =  0;
    		queenCount(0);
    		System.out.println("#"+t+" "+count);
    	}
    }
}