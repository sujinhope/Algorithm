package GitUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주의!! 입력으로 받는 방향 : 북동남서, 회전 방향: 북서남동 
public class Main_14503_로봇청소기 {
	public static int N, M, cnt = 0;
	public static int map[][];
	public static int dr[] = {-1, 0, 1, 0}, dc[] = {0, -1, 0, 1};//북,서,남,동
	
	public static void go(int x, int y, int dir) {
		boolean clean = false;
		if(map[x][y] == 0) {
			map[x][y] = -1; //현재 위치 청소
			cnt++;
		}
		for(int i = dir + 1; i < dir+5; i++) {//왼쪽 방향부터 시작
			int d = i % 4;
			int nx = x + dr[d];
			int ny = y + dc[d];
			//외곽은 전부 벽이기 때문에 벗어났는지 체크할 필요ㄴㄴ
			if(map[nx][ny]==0){
				//청소할 공간을 찾았다면
				clean = true;
				go(nx, ny, d);//전진 + 회전
				break;
			}
			
		}
		if(clean == false) { //청소를 못했다면
			int d = (dir+2)%4; //반대방향
			int nx = x + dr[d];
			int ny = y + dc[d];
			if(map[nx][ny] == 1) { //벽일 경우에
				return;
			}
			go(nx, ny, dir); //방향은 유지
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken()); //행 개수
		M = Integer.parseInt(st.nextToken()); //열 개수
		map = new int[N][M];
		
		//로봇 청소기 위치, 배열(0,1,2,3-북,서,남,동)
		int x, y, dir;
		st = new StringTokenizer(bf.readLine());
		x = Integer.parseInt(st.nextToken()); //x
		y = Integer.parseInt(st.nextToken()); //y
		dir = Integer.parseInt(st.nextToken()); //입력-북,동,남,서
		
		if(dir==1) dir = 3;
		else if(dir==3) dir = 1;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		go(x, y, dir);
		System.out.println(cnt);
	}
}
