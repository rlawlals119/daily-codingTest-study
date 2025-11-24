package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        int max = 10000;
        int[] dp = new int[max + 1];

        dp[0] = 1;
        for (int i = 1; i <= max; i++) {
            dp[i] += dp[i-1];
        }

        // 2 작은 애들의 dp 값에 2를 추가하면 (1,2)로만 만든다
        for (int i = 2; i <= max; i++) {
            dp[i] += dp[i - 2];
        }

        // 3 작은 애들의 dp 값에 3을 추가하면 (1,2,3)으로만 만든다
        for (int i = 3; i <= max; i++) {
            dp[i] += dp[i - 3];
        }

        for (int i = 0; i < T; i++) {
            int x = Integer.parseInt(bf.readLine());
            System.out.println(dp[x]);
        }
    }

}
