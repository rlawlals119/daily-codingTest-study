package donguk.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1253 {
    static ArrayList<Integer> numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        numbers = new ArrayList<>();

        //        N이 2 일떄
        if (N == 2) {
            System.out.println(cnt);
            return;
        }

        StringTokenizer sz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(sz.nextToken()));
        }
        Collections.sort(numbers);

//        System.out.println(numbers);
        for (int i = 0; i < N; i++) {
            int myIdx = i;
            int myValue = numbers.get(myIdx);
            int left = 0;
            int right = N - 1;

            while (left < right) {
//                서로 다른 두 수 check
//                이렇게하면 left ++ right -- 했을때 left<right 가 벗어났는데 답을 확인할 수 있음
//                그러면 결국 left ++ right -- 를 마지막에 하고 while 돌때 조건 확인해야하는데
                int sum = numbers.get(left) + numbers.get(right);
                if (sum == myValue) {
                    if (left == myIdx) {
                        left++;
                        continue;//continue 한 이유는 cnt++ 을 안하기 위해서
                    }
                    if (right == myIdx) {
                        right--;
                        continue;
                    }
//                    System.out.println("sum = " + sum);
                    cnt++;
                    break; //break 한 이유는 방법을 물어본게 아니라 좋은 수의 갯수를 물어본거여서 찾으면 바로 break
                } else if (sum > myValue) right--;
                else left++;

            }

        }

        System.out.println(cnt);
    }
}
