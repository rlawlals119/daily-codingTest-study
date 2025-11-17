package donguk;

import java.util.*;

public class PG_연속된부분수열의합 {
    public int[] solution(int[] sequence , int k ) {
        int n = sequence.length;
        int left =0 , right = 0;
        int total = sequence[0];
        int[] answer = new int[2];
        int minLen = Integer.MAX_VALUE;

        while (right< n ) {
            if (total == k) {
                int len = right - left;
                if (len < minLen) {
                    minLen = len;
                    answer[0] = left;
                    answer[1] = right;
                }
                total -= sequence[left];
                left += 1;
            } else if (total < k) {
                right++;
                if (right < n) {
                    total += sequence[right];
                }
            } else {
                total -= sequence[left];
                left++;
            }

        }

        return answer;

    }


    public static void main(String[] args) {
        PG_연속된부분수열의합 test = new PG_연속된부분수열의합();
        System.out.println("test = " + Arrays.toString(test.solution(new int[]{1, 2, 3, 4, 5}, 7)) );
        System.out.println("test = " + Arrays.toString(test.solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5)) );
        System.out.println("test = " + Arrays.toString(test.solution(new int[]{2, 2, 2, 2, 2}, 6)) );
    }


}
