package donguk.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int[] numbers = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(bf.readLine());
        }


        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 1;
        for (int i = 0; i < N; i++) {
            if (dp[i]> result) result = dp[i];
        }
        System.out.println(N-result);


    }
}
