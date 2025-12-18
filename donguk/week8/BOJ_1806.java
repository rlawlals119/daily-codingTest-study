package donguk.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1806 {
    static StringTokenizer sz;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sz = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int S = Integer.parseInt(sz.nextToken());

        sz = new StringTokenizer(bf.readLine());

        int[] numbers = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(sz.nextToken());
            numbers[i] = tmp;
        }

        int left = 0;
        int right = 0;
        int total = 0;
        int minLength = Integer.MAX_VALUE;

        while (left <= N && right <= N) {
            if (total < S) {
                total += numbers[right];
                right++;
            } else {
                if(minLength>right-left)
                    minLength = right - left;
                total -= numbers[left];
                left++;
            }
        }

        if(minLength == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(minLength);


    }
}
