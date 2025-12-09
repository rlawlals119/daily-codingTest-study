package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz;

        sz = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(sz.nextToken());
        ArrayList<Integer> list = new ArrayList<>();

        sz = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(sz.nextToken()));
        }

////        입력값 체크
//        System.out.println(list);

        int left = 0;
        int right = N - 1;

        int bestSum = Integer.MAX_VALUE;
        int bestL = 0;
        int bestR = 0;

        while (left < right) {
            int sum = list.get(right) + list.get(left);

            if (Math.abs(sum) < bestSum) {
                bestL = list.get(left);
                bestR = list.get(right);
                bestSum = Math.abs(sum);
            }

            if (sum > 0) right--;
            else left++;

        }
        StringBuilder sb = new StringBuilder();
        sb.append(bestL).append(' ').append(bestR);
        System.out.println(sb);



    }
}
