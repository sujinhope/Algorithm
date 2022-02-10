import java.util.*;

public class Test42889 {

  public static int n = 5;
  public static int stages[] = {2, 1, 2, 6, 2, 4, 3, 3}; // {3, 4, 2, 1, 5}
  // public static int n = 4;
  // public static int stages[] = {4, 4, 4, 4}; // {4, 1, 2, 3}
  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(n, stages)));
  }

  public static int[] solution(int N, int[] stages) {
    int[] answer = new int[N];
    int[] peopleInStage = new int[N+2]; // 모든 맵 완료(N+1)
    double[][] failureRate = new double[N+1][2];

    for(int i = 0; i<stages.length; i++) {
      peopleInStage[stages[i]]++;
    }

    int proceeding, arrival = peopleInStage[N+1];
    for(int i = N; i>0; i--) {
      proceeding = peopleInStage[i];
      arrival += proceeding;
      
      failureRate[i][0] = i;
      failureRate[i][1] = arrival == 0 ? 0 : (double)proceeding / arrival;
    }

    failureRate = Arrays.copyOfRange(failureRate, 1, failureRate.length);

    Arrays.sort(failureRate, new Comparator<double[]>() {
      @Override
      public int compare(double[] o1, double[] o2) {
        if(o1[1] == o2[1]) {
          return o1[0] < o2[0] ? -1 : 1;
        } else {
          return o1[1] < o2[1] ? 1 : -1;
        }
      }
    });

    for(int i = 0; i<failureRate.length; i++) {
      answer[i] = (int)failureRate[i][0];
    }

    return answer;
  }
  
}
