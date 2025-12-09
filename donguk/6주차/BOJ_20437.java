package donguk;

import java.io.*;
import java.util.*;

public class BOJ_20437 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            ArrayList<Integer>[] pos = new ArrayList[26];
            for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();

            for (int i = 0; i < W.length(); i++) {
                pos[W.charAt(i) - 'a'].add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for (int i = 0; i < 26; i++) {
                if (pos[i].size() < K) continue;

                for (int j = 0; j <= pos[i].size() - K; j++) {
                    int start = pos[i].get(j);
                    int end = pos[i].get(j + K - 1);

                    int len = end - start + 1;

                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }

            if (max == -1) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}
