package byunghyeon.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22251 {

    static int[] numDigit = {   0b0111111, 0b0001100, 0b1110110, 0b1011110,
                                0b1001101, 0b1011011, 0b1111011, 0b0001110,
                                0b1111111, 0b1011111 };
    static int[][] change = new int[10][10];
    static int N,K,P,X;
    static int[] display;
    static int cases = 0;
    static boolean[] registered;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 최대 층 수 (1~N)
        K = Integer.parseInt(st.nextToken()); // 층수를 보여주는 디스플레이의 K자리 수
        P = Integer.parseInt(st.nextToken()); // 최소 1개, 최대 P개 반전
        X = Integer.parseInt(st.nextToken()); // X층에서 멈췄을 때 반전 시킬 LED 고르기

        // 각 세그먼트마다 바꿔야하는 개수 구하기
        for(int i = 0; i <= 9; i++){
            for(int j = 0; j <= 9; j++){
                change[i][j] = Integer.bitCount(numDigit[i]^numDigit[j]);
            }
        }

        // 1. X를 K자리의 디스플레이로 만든다.
        registered = new boolean[(int)Math.pow(10, K) + 1];
        String[] ints = String.valueOf(X).split("");
        display = new int[K];
        for(int i = 0; i < ints.length; i++){
            display[i+(K-ints.length)] = Integer.parseInt(ints[i]);
        }

        reverseSegment(0, 0);
        System.out.println(cases);
    }

    public static void reverseSegment(int revNum, int stage){ // 몇개 반전,

        if(stage >= K) return;

        int origin = display[stage];
        for(int i = 0; i <= 9; i++){
            int changeCount = revNum + change[origin][i];
            if(changeCount > P) continue;

            display[stage] = i;
            int num = arrToInt(display);
            if(num >= 1 && num <= N && num != X && !registered[num]){
                registered[num] = true;
                cases++;
            }
            reverseSegment(changeCount, stage+1);
            display[stage] = origin;
        }
    }

    public static int arrToInt(int[] arr){

        int sum = 0;
        int x = (int) Math.pow(10, arr.length-1);
        int idx = 0;
        while(x >= 1){
            sum += arr[idx++] * x;
            x/=10;
        }
        return sum;
    }
}