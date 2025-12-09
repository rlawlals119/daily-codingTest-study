package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] height = new int[N];
        for(int i = 0; i < N; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];

        Stack<int []> stack = new Stack<>();
        for(int i = 0; i < N; i++){
            int cur = height[i];

            while(!stack.empty() && cur > stack.peek()[0]){
                stack.pop();
            }


            if(stack.empty()){
                answer[i] = 0;
            }
            else answer[i] = stack.peek()[1]+1;

            stack.push(new int[]{cur, i});
        }


        StringBuilder ans = new StringBuilder();
        for (int i : answer) {
            ans.append(i).append(" ");
        }
        ans.append("\n");
        System.out.print(ans);
    }
}
