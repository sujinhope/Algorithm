package kakao;

import java.util.*;

public class Test72410 {
    // https://programmers.co.kr/learn/courses/30/lessons/72410

    // static String new_id = "...!@BaT#*..y.abcdefghijklm"; // "bat.y.abcdefghi"
    // static String new_id = "z-+.^."; // "z--"
    // static String new_id = "=.="; // "aaa"
    // static String new_id = "123_.def"; // "123_.def"
    static String new_id = "abcdefghijklmn.p"; // "abcdefghijklmn"

    public static void main(String[] args) {
        solution(new_id);
    }

    public static String solution(String new_id) {
        String answer = "";

        // 1. 대문자 -> 소문자
        new_id = new_id.toLowerCase();

        // 2. 소문자, 숫자, -_.를 제외한 모든 문자열 제거
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");

        // 3. ..., .. -> .
        new_id = new_id.replaceAll("[.]+", ".");

        // 4. 처음, 끝에 위치한 . 제거
        if(new_id.startsWith(".")) {
            new_id = new_id.substring(1);
        }
        if(new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length()-1);
        }

        // 5. 빈 문자열일 경우 a 대입
        if(new_id.length() == 0) {
            new_id = "a";
        }

        // 6. 길이가 16일 경우 16번째 글자 이후 모든 글자 날리기 + 끝 문자가 .일 경우 날리기
        new_id = new_id.substring(0, Math.min(15, new_id.length()));
        if(new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length()-1);
        }

        // 7. 2글자 이하일 경우 3글자가 될때까지 끝 문자 반복
        if(new_id.length() <= 2) {
            String last = new_id.charAt(new_id.length()-1)+"";
            do {
                new_id = new_id.concat(last);
            } while(new_id.length() < 3);
        }

        return answer;
    }

}