package donguk.추가공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {
    static StringTokenizer sz;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sz = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int M = Integer.parseInt(sz.nextToken());
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(bf.readLine());
        }

        int left = 0;
        int right = 0;
        int diff = 0;
        int result = Integer.MAX_VALUE;
        Arrays.sort(numbers);

        while (right < N) {
            if (left == right) {
                right++;
                continue;
            }

            diff = numbers[right] - numbers[left];

            if (diff >= M) {
                result = Math.min(result, diff);
                left++;
            } else right++;
        }


        System.out.println(result);
    }
}

