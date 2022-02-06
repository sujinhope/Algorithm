package kakao;

import java.util.*;

public class Test81301 {
    // https://programmers.co.kr/learn/courses/30/lessons/81301

    // static String s = "one4seveneight"; // 1478
    // static String s = "23four5six7"; // 234567
    // static String s = "2three45sixseven"; // 234567
    static String s = "123"; // 123

    public static void main(String[] args) {
        solution(s);
    }

    public static int solution(String s) {

        Map<String, String> wordToNum = Map.of(
            "zero", "0",
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
        );

        for(String key: wordToNum.keySet()) {
            s = s.replaceAll(key, wordToNum.get(key));
        }

        return Integer.parseInt(s);
    }
}
