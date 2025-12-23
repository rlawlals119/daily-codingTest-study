package donguk.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader  bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(sz.nextToken());
            numbers[i] = tmp;
        }

        int left = 0;
        int right = 0;
        long result = 0;
        HashSet<Integer> set = new HashSet<>();

        while (left < N) {
//            System.out.println("left = " + left);
//            System.out.println("right = " + right);
//            System.out.println("--------------------");
            if (right >= N) {
                result += (right - left);
//                System.out.println("⭐⭐⭐⭐⭐⭐⭐result = " + result);
                set.remove(numbers[left]);
                left++;
            } else if (set.contains(numbers[right])) {
                result += (right - left);
//                System.out.println("⭐⭐⭐⭐⭐⭐⭐result = " + result);
                set.remove(numbers[left]);
                left++;
            } else {
                set.add(numbers[right]);
                right++;
            }

        }
        System.out.println(result);
    }
}
