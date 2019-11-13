package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2239_스도쿠 {
	
	static int sudoku[][] = new int[9][9];
	static boolean flag = true, finish = false;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		int sr = 0, sc = 0;
		for(int i = 0; i<9; i++) {
			String s = bf.readLine();
			for(int j = 0; j<9; j++) {
				sudoku[i][j] = s.charAt(j)-'0';
				if(sudoku[i][j]==0 && flag) {
					sr = i;
					sc = j;
					flag = false;
				}
			}
		}
		
		back(0, 0); //0, 0이면 안됨! 수정!
		
//		for(int i = 0; i<9; i++) {
//			for(int j = 0; j<9; j++) {
//				System.out.print(sudoku[i][j]);
//			}
//			System.out.println();
//		}
		
	}

	private static void back(int r, int c) {
		if(finish) {
			return;
		}
		
		int nr = -1, nc = -1;
		top:
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				if(sudoku[i][j]==0) {
					nr = i;
					nc = j;					
					break top;
				}
			}
		}
		if(nr==-1) {
			finish = true;
			for(int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			return;
		}
		for(int k = 1; k<=9; k++) {
			if(chkRow(nr, k) && chkCol(nc, k) && chkGroup(nr, nc, k)) {
				sudoku[nr][nc] = k;
				back(nr, nc);
				sudoku[nr][nc] = 0;
			}
		}
	}

	private static boolean chkGroup(int i, int j, int k) {
		int sr = 3*(i/3);
		int sc = 3*(j/3);
		for(int r = sr; r<sr+3; r++) {
			for(int c = sc; c<sc+3; c++) {
				if(sudoku[r][c]==k)
					return false;
			}
		}
		return true;
	}

	private static boolean chkCol(int j, int k) {
		for(int i = 0; i<9; i++) {
			if(sudoku[i][j]==k)
				return false;
		}
		return true;
	}

	private static boolean chkRow(int i, int k) {
		for(int j = 0; j<9; j++) {
			if(sudoku[i][j]==k) 
				return false;
		}
		return true;
	}
	
}







