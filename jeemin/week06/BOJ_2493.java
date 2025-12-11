package jeemin.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] tops = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tops[i] = Integer.parseInt(s[i - 1]);
        }

        int[] ans = new int[n + 1];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{tops[n], n});
        for (int i = n - 1; i > 0; --i) {
            while (!stack.isEmpty() && stack.peek()[0] <= tops[i]) {
                int[] top = stack.pop();    // 신호 보내는 탑
                ans[top[1]] = i;    // 신호 받는 탑 인덱스
            }
            stack.push(new int[]{tops[i], i});
        }
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            ans[top[1]] = 0;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
