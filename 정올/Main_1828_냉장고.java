import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
 
public class Main_1828_냉장고 {
    static class Pair {
        int min, max;
        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
         
        int list[][] = new int[N][2];
         
        for(int i = 0; i<N; i++) {
            list[i][0] = sc.nextInt();
            list[i][1] = sc.nextInt();
        }
         
        Arrays.sort(list, new Comparator<int[]>() {
            public int compare(int[]a, int[]b) {
                return a[0]- b[0];
            }
        });
         
        List<Pair> Ref = new ArrayList<Pair>();
        int RefIdx = 0;
        Ref.add(new Pair(list[0][0], list[0][1]));
        for(int i = 1; i<list.length; i++) {
            if(list[i][0] > Ref.get(RefIdx).max) {
                RefIdx++;
                Ref.add(new Pair(list[i][0], list[i][1]));
                continue;
            } else {
                int RefMin = list[i][0], RefMax = Ref.get(RefIdx).max;
                if(list[i][1] < RefMax) { //냉장고 범위 조절하기
                    RefMax = list[i][1];
                }
                Ref.set(RefIdx, new Pair(RefMin, RefMax));
            }
        }
         
        System.out.println(RefIdx + 1);
    }
 
}
