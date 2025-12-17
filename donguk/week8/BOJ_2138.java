package donguk.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138 {
    static int N;
    static String current ,goal;
    static boolean[] goalList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        current = bf.readLine();
        goal = bf.readLine();


        goalList = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (goal.charAt(i) == '1') goalList[i] = true;
            else goalList[i] = false;
        }

        int x = check(true);
        int y = check(false);

        if(x== -1 && y == -1) System.out.println(-1);
        else if (x== -1) System.out.println(y);
        else if (y == -1) System.out.println(x);
        else System.out.println(Math.min(x, y));
    }

    static int check(boolean flag) {
        int cnt = 0;
        boolean[] currentList = new boolean[N];

        for (int i = 0; i < N; i++) {
            if(current.charAt(i) == '1') currentList[i] = true;
            else currentList[i] = false;
        }

        if (flag) {
            currentList[0] = !currentList[0];
            currentList[1] = !currentList[1];
            cnt++;

            for (int i = 1; i <N; i++) {
                if (currentList[i - 1] != goalList[i - 1]) {
                    if (i + 1 < N) {
                        currentList[i - 1] = !currentList[i - 1];
                        currentList[i] = !currentList[i];
                        currentList[i + 1] = !currentList[i + 1];
                        cnt++;
                    } else {
                        currentList[i - 1] = !currentList[i - 1];
                        currentList[i] = !currentList[i];
                        cnt++;
                    }
                }
            }
        } else {
            for (int i = 1; i <N; i++) {
                if (currentList[i - 1] != goalList[i - 1]) {
                    if (i + 1 < N) {
                        currentList[i - 1] = !currentList[i - 1];
                        currentList[i] = !currentList[i];
                        currentList[i + 1] = !currentList[i + 1];
                        cnt++;
                    } else {
                        currentList[i - 1] = !currentList[i - 1];
                        currentList[i] = !currentList[i];
                        cnt++;
                    }
                }
            }

        }

        boolean result = true;
        for (int i = 0; i < N; i++) {
            if(currentList[i] != goalList[i]) result = false;
        }

        if (result) {
            return cnt;
        } else {
            return -1;
        }

    }


}
