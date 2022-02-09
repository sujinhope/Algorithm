import java.util.*;

public class Test64061 {

  static int[][] b = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
  static int[] m = {1,5,3,5,1,2,1,4}; // 4

  public static void main(String[] args) {
    solution(b, m);
  }

  public static int solution(int[][] board, int[] moves) {
    int answer = 0;
    int length = board.length;

    Stack<Integer> box = new Stack<>();
    Stack<Integer>[] board_list = new Stack[length];

    for(int i = 0; i<length; i++) {
      board_list[i] = new Stack<Integer>();
      for(int j = length-1; j>=0; j--) {
        if(board[j][i] != 0) {
          board_list[i].push(board[j][i]);
        }
      }
    }

    for(int i = 0; i<moves.length; i++) {
      int index = moves[i] - 1;

      if(!board_list[index].empty()) {
        int now = board_list[index].pop();
        if(box.size() > 0 && box.peek() == now) {
          box.pop();
          answer+=2;
        } else {
          box.push(now);
        }
      }
    }

    return answer;
  }
}
