package Level2;

import java.util.*;

public class Test42888 {

  // https://programmers.co.kr/learn/courses/30/lessons/42888

  static String record[] = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(record)));
  }

  public static String[] solution(String[] record) {
    List<String> result = new LinkedList<>();
    String[][] message = new String[record.length][2];

    HashMap<String, String> nicknames = new HashMap<>();
    for(int i = 0; i<record.length; i++) {
      StringTokenizer st = new StringTokenizer(record[i]);
      message[i][0] = st.nextToken();
      message[i][1] = st.nextToken();

      if(message[i][0].equals("Leave")) continue;
      nicknames.put(message[i][1], st.nextToken());
    }

    for(int i = 0; i<record.length; i++) {
      String nickname = nicknames.get(message[i][1]);
      String state = message[i][0];
      if(state.equals("Enter")) {
        result.add(nickname+"님이 들어왔습니다.");
      } else if(state.equals("Leave")) {
        result.add(nickname+"님이 나갔습니다.");
      }
    }

    return result.toArray(new String[result.size()]);
  }

}
