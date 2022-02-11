import java.util.*;

public class Test17682 {

  // https://programmers.co.kr/learn/courses/30/lessons/17682

  // static String dart = "1S2D*3T"; // 37
  // static String dart = "1D2S#10S"; // 9
  // static String dart = "1D2S0T"; // 3
  // static String dart = "1S*2T*3S"; // 23
  // static String dart = "1D#2S*3S"; // 5
  // static String dart = "1T2D3D#"; // -4
  static String dart = "1D2S3T*"; // 59

  public static void main(String[] args) {
    System.out.println(solution(dart));
  }

  public static int solution(String dartResult) {
    int answer = 0;
    Stack<Integer> scores = new Stack<>();

    for (int i = 0, length = dartResult.length(); i < length; i++) {
      int score = dartResult.charAt(i) - '0';
      if (score == 1) {
        int nextNum = dartResult.charAt(i + 1) - '0';
        if (nextNum == 0) {
          score = 10;
          i++;
        }
      }

      i++;
      char bonus = dartResult.charAt(i);
      if (bonus == 'D') {
        score *= score;
      } else if (bonus == 'T') {
        score *= (score * score);
      }

      i++;
      if (i >= dartResult.length()) {
        scores.add(score);
        break;
      }

      char option = dartResult.charAt(i);
      if (option >= '0' && option <= '9') {
        i--;
        scores.add(score);
        continue;
      }
      if (option == '*') {
        if (scores.size() > 0) {
          int before = scores.pop();
          scores.add(before * 2);
        }
        scores.add(score * 2);
      } else if (option == '#') {
        scores.add(score * -1);
      }

    }

    while (!scores.isEmpty()) {
      answer += scores.pop();
    }
    return answer;
  }
}
