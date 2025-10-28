package jeemin.week01;

import java.util.Stack;

class LC_739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] output = new int[temperatures.length];

        Stack<int[]> stack = new Stack<>();

        stack.push(new int[]{0, temperatures[0]});
        for (int i = 1; i < temperatures.length; i++){
            while (!stack.isEmpty() && stack.peek()[1] < temperatures[i]) {
                int[] temp = stack.pop();
                output[temp[0]] = i - temp[0];
            }
            // stack이 비어있거나 맨위에 더 높은 온도가 있을 경우, 현재 온도 stack에 추가
            stack.push(new int[]{i, temperatures[i]});
        }

        return output;
    }
}