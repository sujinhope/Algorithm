package kakao;

import java.util.*;

public class Test92334 {
    // https://programmers.co.kr/learn/courses/30/lessons/92334

    static String id_list[] = {"muzi", "frodo", "apeach", "neo"};
    static String report[] = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
    static int k = 2;

    public static void main(String[] args) {
        
        solution(id_list, report, k);

    }
    
    public static int[] solution(String[] id_list, String[] report, int k) {
        
        int report_count[][] = new int[id_list.length][id_list.length];

        Map<String, Integer> id_num = new HashMap<>();
        for(int i = 0; i<id_list.length; i++) {
            id_num.put(id_list[i], i);
        }

        StringTokenizer st;
        for(int i = 0; i<report.length; i++) {
            st = new StringTokenizer(report[i]);
            int from = id_num.get(st.nextToken());
            int to = id_num.get(st.nextToken());
            report_count[from][to] = 1;
        }

        for(int i = 0; i<report_count.length; i++) {
            System.out.println(Arrays.toString(report_count[i]));
        }

        int[] report_sum = new int[id_list.length];
        for(int i = 0; i<report_count.length; i++) {
            for(int j = 0; j<report_count.length; j++) {
                report_sum[j] += report_count[i][j];
            }
        }

        System.out.println();
        System.out.println(Arrays.toString(report_sum));
        System.out.println();

        int answer[] = new int[id_list.length];
        for(int i = 0; i<report_count.length; i++) {
            for(int j = 0; j<report_count.length; j++) {
                if(report_count[i][j] == 1 && report_sum[j] >= k) {
                    answer[i]++;
                }
            }
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }

}
