package jeemin.week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n <= 5) {
                System.out.println(n);
                continue;
            }
            int[] dp1 = new int[n + 1];
            int[] dp2 = new int[n + 1];
            int[] dp3 = new int[n + 1];

            // 1~3 dp 초기화
            dp1[1] = 1;
            dp1[2] = 1;
            dp1[3] = 2;
            dp2[2] = 1;
            dp3[3] = 1;
            for (int j = 4; j <= n; j++) {
                dp1[j] = dp1[j - 1] + dp2[j - 1] + dp3[j - 1];
                dp2[j] = dp2[j - 2] + dp3[j - 2];
                dp3[j] = dp3[j - 3];
            }
            System.out.println(dp1[n] + dp2[n] + dp3[n]);
        }
    }
}
