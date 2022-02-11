import java.util.*;

public class Test17681 {

  // https://programmers.co.kr/learn/courses/30/lessons/17681

  static int n = 5;
  static int arr1[] = {9, 20, 28, 18, 11};
  static int arr2[] = {30, 1, 21, 17, 28}; // "#####","# # #", "### #", "# ##", "#####"
  // static int n = 6;
  // static int arr1[] = {46, 33, 33 ,22, 31, 50};
  // static int arr2[] = {27 ,56, 19, 14, 14, 10}; // "######", "### #", "## ##", " #### ", " #####", "### # "

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(n, arr1, arr2)));
  }

  public static String[] solution(int n, int[] arr1, int[] arr2) {
    String[] answer = new String[n];

    char[][] map1 = changeArray(n, arr1);
    char[][] map2 = changeArray(n, arr2);
    
    for(int i = 0; i<n; i++) {
      StringBuilder sb = new StringBuilder();
      for(int j = 0; j<n; j++) {
        if(map1[i][j] == '1' || map2[i][j] == '1') {
          sb.append('#');
        } else {
          sb.append(' ');
        }
      }
      answer[i] = sb.toString();
    }

    return answer;
  }

  public static char[][] changeArray(int n, int[] arr) {
    char[][] result = new char[n][n];

    for(int i = 0; i<n; i++) {
      String binary = DecimalToBinary(n, arr[i]);
      System.out.println(binary);
      for(int j = 0; j<n; j++) {
        result[i][j] = binary.charAt(j);
      }
    }
    System.out.println();

    return result;
  }

  public static String DecimalToBinary(int n, int num) {
    StringBuilder result = new StringBuilder();

    while(num > 0) {
      result.insert(0, num % 2);
      num /= 2;
    }
    while(result.length() < n) {
      result.insert(0, '0');
    }

    return result.toString();
  }

}
