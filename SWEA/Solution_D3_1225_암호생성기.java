package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution_D3_1225_암호생성기 {
 
    public static void main(String[] args) throws IOException {
         
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        for(int t = 1; t<=10; t++) {
            String T = bf.readLine();
            Queue<Integer> q = new LinkedList<>();
             
            st = new StringTokenizer(bf.readLine());
            for(int i = 0; i<8; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }
             
            int cnt = 1, front = 0;
            while(true) {
                front = q.poll() - cnt++;
                if(front <= 0) {
                    front = 0;
                    q.offer(front);
                    break;
                }
                q.offer(front);
                if(cnt == 6) cnt = 1;
            }
            System.out.print("#"+t);
            for(int i = 0; i<8; i++) {
                System.out.print(" " + q.poll());
            }
            System.out.println();
        }
         
    }
 
}