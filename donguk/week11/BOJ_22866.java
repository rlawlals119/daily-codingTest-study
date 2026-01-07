package donguk.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer sz = new StringTokenizer(bf.readLine());

        int[] numbers = new int[N];
        ArrayList<Integer>[] indexes = new ArrayList[N];
        int[] cnt = new int[N];

        for (int i = 0; i < N; i++) {
            indexes[i] = new ArrayList<>();
            int number = Integer.parseInt(sz.nextToken());
            numbers[i] = number;
        }

        Stack<Integer> stack = new Stack<>();
        Arrays.fill(cnt, 0);

        //왼쪽->오른쪽
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] <= numbers[i]) {
                stack.pop();
            }

            cnt[i] += stack.size();
            if(!stack.isEmpty()) indexes[i].add(stack.peek());
            stack.push(i);
        }

        stack.clear();

        //오른쪽 -> 왼쪽
        for (int i = N-1; i >=0; i--) {
            while (!stack.isEmpty() && numbers[stack.peek()]<= numbers[i]) {
                stack.pop();
            }

            cnt[i] += stack.size();
            if(!stack.isEmpty()) indexes[i].add(stack.peek());
            stack.push(i);
        }

        //가장 가까운 인덱스 조사 및 출력
        for (int i = 0; i < N; i++) {
            if (cnt[i] == 0) {
                System.out.println(0);
            } else {
                int tmp = indexes[i].get(0);
                for (int idx : indexes[i]) {
                    if(Math.abs(idx-i) < Math.abs(tmp-i))
                        tmp = idx;
                }

                System.out.println(cnt[i]+" "+(tmp+1));
            }
        }
    }
}
