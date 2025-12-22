package byunghyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1253 {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // (1~2000)

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int[] ns = new int[N]; // (1~10억)
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            ns[i] = num;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(ns);
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++) {
                int n1 = ns[i];
                int n2 = ns[j];
                int sum = n1 + n2;

                if (set.contains(sum)) continue;

                if (n1 == n2 && n2 == sum) {
                    if (map.get(sum) > 2) set.add(sum);
                } else if (n1 == sum) {
                    if (map.get(sum) > 1) set.add(sum);
                } else if (n2 == sum) {
                    if (map.get(sum) > 1) set.add(sum);
                } else {
                    set.add(sum);
                }
            }
        }

        Set<Integer> dup = new HashSet<>();
        for(int i = 0; i < N; i++){
            int e = ns[i];
            if(set.contains(e) && !dup.contains(e)){
                dup.add(e);
                count += map.get(ns[i]);
            }
        }


        System.out.println(count);
    }
}
