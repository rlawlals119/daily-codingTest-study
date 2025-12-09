package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] heights = new int[N];
        StringTokenizer sz = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            heights[i] = Integer.parseInt(sz.nextToken());
        }

        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[i] = stack.peek() + 1;
            } else {
                result[i] = 0;
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
