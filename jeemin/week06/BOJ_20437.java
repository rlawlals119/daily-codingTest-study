package jeemin.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] str = br.readLine().split("");
            int k = Integer.parseInt(br.readLine());
            HashMap<String, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < str.length; j++) {
                map.putIfAbsent(str[j], new ArrayList<>());
                map.get(str[j]).add(j);
            }
// 0, 3, 10, 11, 13
            int max = -1;
            int min = 100001;
            for (List<Integer> value : map.values()) {
                if (value.size() >= k) {
                    for (int j = 0; j <= value.size() - k; j++) {
                        max = Math.max(max, value.get(j + k - 1) - value.get(j) + 1);
                        min = Math.min(min, value.get(j + k - 1) - value.get(j) + 1);
                    }
                }
            }

            if (max == -1) { System.out.println(max); continue; }
            System.out.println(min + " " + max);
        }
    }
}
