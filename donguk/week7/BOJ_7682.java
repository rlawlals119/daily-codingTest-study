package donguk.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7682 {
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        numbers = new int[]{0b0001000, 0b1101110, 0b1000001, 0b1000100, 0b0100110, 0b0010100, 0b0010000, 0b1001110, 0b0000000, 0b0000100};

        int N = Integer.parseInt(sz.nextToken());
        int K = Integer.parseInt(sz.nextToken());
        int P = Integer.parseInt(sz.nextToken());
        int X = Integer.parseInt(sz.nextToken());
        int result = 0;
        String newX = String.format("%0" + K + "d", X);

        for (int i = 1; i <= N; i++) {
            if(i == X) continue;
            int diff = calculate(newX, i);
            if (diff>=1 && diff <= P) result++;
        }
//        System.out.println(newX);
        System.out.println(result);


    }

    static int calculate(String newX , int num) {
        int cnt = 0;
        for (int i = newX.length()-1; i >= 0 ; i--) {
            int a = newX.charAt(i) - '0';
            int b = num % 10;
            cnt += Integer.bitCount(numbers[a] ^ numbers[b]);
            num /= 10;
        }
        return cnt;
    }


}

