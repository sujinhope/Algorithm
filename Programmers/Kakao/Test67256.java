package kakao;

public class Test67256 {

    // https://programmers.co.kr/learn/courses/30/lessons/67256

    // static int[] ns = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
    // static String h = "right"; // LRLLLRLLRRL
    // static int[] ns = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
    // static String h = "left"; // LRLLRRLLLRR
    static int[] ns = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    static String h = "right"; // LLRLLRLLRL

    public static void main(String[] args) {
        solution(ns, h);
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";

        int lr = 3;
        int lc = 0;
        int rr = 3;
        int rc = 2;
        int l_distance = -1;
        int r_distance = -1;

        int keypad_position[][] = {{3, 1}
                                    , {0, 0}, {0, 1}, {0, 2}
                                    ,{1, 0}, {1, 1}, {1, 2}
                                    ,{2, 0}, {2, 1}, {2, 2}
                                    ,{3, 0}, {3, 2}};

        StringBuffer sb = new StringBuffer();
        int now, now_r, now_c;
        for(int i = 0; i<numbers.length; i++) {
            now = numbers[i];
            now_r = keypad_position[now][0];
            now_c = keypad_position[now][1];

            if(now == 1 || now == 4 || now == 7) {
                sb.append("L");
                lr = now_r;
                lc = now_c;
            } else if(now == 3 || now == 6 || now == 9) {
                sb.append("R");
                rr = now_r;
                rc = now_c;
            } else {
                l_distance = Math.abs(lr - now_r) + Math.abs(lc - now_c);
                r_distance = Math.abs(rr - now_r) + Math.abs(rc - now_c);
                if(l_distance == r_distance) {
                    if(hand.equals("right")) {
                        sb.append("R");
                        rr = now_r;
                        rc = now_c;
                    } else {
                        sb.append("L");
                        lr = now_r;
                        lc = now_c;
                    }
                } else if(l_distance < r_distance) {
                    sb.append("L");
                    lr = now_r;
                    lc = now_c;
                } else {
                    sb.append("R");
                    rr = now_r;
                    rc = now_c;
                }
            }
        }
        answer = sb.toString();
        System.out.println(answer);
        return answer;
    }

}
