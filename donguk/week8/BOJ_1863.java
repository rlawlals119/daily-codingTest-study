package donguk.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1863 {
    static StringTokenizer sz;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            sz = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(sz.nextToken());
            int y = Integer.parseInt(sz.nextToken());
            nodes.add(y);
        }

//        System.out.println(Arrays.deepToString(nodes.toArray()));

        Stack<Integer> stack = new Stack<>();
        int cnt = 0;

        for (int curr : nodes) {
            while (!stack.isEmpty() && stack.peek() > curr) {
                stack.pop();
                cnt++;
            }
            if(!stack.isEmpty()&&stack.peek() == curr)continue;

            if(curr!=0)stack.push(curr);
        }

        while(!stack.isEmpty()){
            cnt++;
            stack.pop();
        }

        System.out.println(cnt);

    }

}
