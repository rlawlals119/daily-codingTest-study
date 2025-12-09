package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
        //어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < T; i++){

            String W = br.readLine();
            int K = Integer.parseInt(br.readLine()); // 특정 문자 포함해야 할 개수

            Map<Character,List<Integer>> map = new HashMap<>();

            for(int j = 0; j < W.length(); j++){
                char ch = W.charAt(j);
                map.putIfAbsent(ch, new ArrayList<>());
                map.get(ch).add(j);
            }

            int max = -1;
            int min = Integer.MAX_VALUE;
            for(List<Integer> list: map.values()){
                for(int j = 0; j < list.size()-K+1; j++){
                    int length = list.get(j+K-1) - list.get(j) + 1;
                    min = Math.min(length, min);
                    max = Math.max(length, max);
                }
            }

            if(max == -1){ // min
                answer.append(-1).append("\n");
            }else{
                answer.append(min).append(" ").append(max).append("\n");
            }
        }

        answer.setLength(answer.length()-1);
        System.out.println(answer);
    }
}
