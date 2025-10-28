package jeemin.week01;

import java.util.Stack;
class PG_주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{prices[0], 0});

        for (int i = 1; i < prices.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] > prices[i]) {
                int[] stock = stack.pop();
                answer[stock[1]] = i - stock[1];
            }
            stack.push(new int[]{prices[i], i});
        }

        while (!stack.isEmpty()) {
            int[] stock = stack.pop();
            answer[stock[1]] = prices.length - 1 - stock[1];
        }
        return answer;
    }
}
