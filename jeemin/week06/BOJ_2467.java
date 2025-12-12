package jeemin.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        int left = 0;
        int right = N - 1;
        int minVal = Integer.MAX_VALUE;
        int[] ans = new int[2];
        while (left < right) {
            if (Math.abs(nums[left] + nums[right]) < Math.abs(minVal)) {
                ans = new int[]{nums[left], nums[right]};
                minVal = nums[left] + nums[right];
//                System.out.println(ans[0] + " " + ans[1]);
//                System.out.println(minVal);
                if (minVal == 0) break;
            }
            if (nums[left] + nums[right] > 0) {
                right--;
            }
            else if (nums[left] + nums[right] < 0) {
                left++;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
