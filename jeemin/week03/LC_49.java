package jeemin.week03;
import java.util.*;

public class LC_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashSet<String> keys = new HashSet<>();
        List<List<String>> answer = new ArrayList<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);

            map.putIfAbsent(sorted, new ArrayList<>());
            map.get(sorted).add(str);
            keys.add(sorted);
        }


        for (String key : keys) {
            answer.add(map.get(key));
        }

        return answer;
        // return new ArrayList<>(map.values());
        // map.values() -> Collection<List<String>> 이기 때문에 ArrayList로 한번 감싸서 반환
    }
}
