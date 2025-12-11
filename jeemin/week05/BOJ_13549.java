package jeemin.week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        if (n == k) {System.out.println(0); return;}
        int[] dp = new int[Math.max(n, k) * 2 + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, k});
        dp[k] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dpN = cur[0];
            int loc = cur[1];
            System.out.println(dpN + " " + loc);
            if (loc == n) break;
            // 순간이동 가능한 경우
            if (loc % 2 == 0 && dpN < dp[loc / 2]) {
                dp[loc / 2] = dpN;
                pq.add(new int[]{dpN, loc / 2});
            }
            // -1 이동
            if (loc - 1 >= 0 && dpN + 1 < dp[loc - 1]) {
                dp[loc - 1] = dpN + 1;
                pq.add(new int[]{dpN + 1, loc - 1});
            }
            // +1 이동
            if (loc + 1 < dp.length && dpN + 1 < dp[loc + 1]) {
                dp[loc + 1] = dpN + 1;
                pq.add(new int[]{dpN + 1, loc + 1});
            }
        }
        System.out.println(dp[n]);
    }
}
