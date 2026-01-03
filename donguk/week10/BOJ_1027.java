package donguk.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1027 {
    static int[] board;
    static Stack<Integer> maxIndex;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(bf.readLine());
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        board = new int[N];

        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(sz.nextToken());
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            int tmp = calculate(i);
            if(tmp>result) result = tmp;
        }
        System.out.println(result);

    }
    static int calculate(int startIdx) {
        int left = 0;
        int right = 0;
        double leftMinTilt = Double.MAX_VALUE;
        double rightMaxTilt = -Double.MAX_VALUE;

        // 왼쪽 계산
        for (int i = startIdx-1; i >=0 ; i--) {
            double tilt = (double) (board[startIdx] - board[i]) / (startIdx - i);
            if (tilt < leftMinTilt) {
                leftMinTilt = tilt;
                left++;
            }
        }

        //오른쪽 계산
        for (int i = startIdx+1; i <N; i++) {
            double tilt = (double) (board[i] - board[startIdx]) / (i - startIdx);
            if (tilt > rightMaxTilt) {
                rightMaxTilt = tilt;
                right++;
            }
        }
        return left+right;
    }
}
