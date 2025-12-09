package donguk.추가공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz;
        int cnt = 0;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        sz = new StringTokenizer(br.readLine());

        if (N == 2) {
            System.out.println(cnt);
            return;
        }

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(sz.nextToken());
            list.add(x);
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i);
            int left = 0 ;
            int right = N-1;
            int myIdx = i;

            while (left < right) {
                int sum = list.get(left) + list.get(right);

                if (sum == x) {
                    if(myIdx==left) {
                        left++;
                        continue;
                    }
                    if (myIdx==right) {
                        right--;
                        continue;
                    }
                    cnt++;
                    break;
                }

                if(sum>x) right--;
                else left++;

            }

        }

        System.out.println(cnt);
    }
}
