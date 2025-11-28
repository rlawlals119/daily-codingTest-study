package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int K = Integer.parseInt(sz.nextToken());

        int[] A = new int[N * 2];
        boolean[] robot = new boolean[N];
        StringTokenizer sz2 = new StringTokenizer(bf.readLine());

        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(sz2.nextToken());
        }

        int result = 0;

        while (true) {
            result++;

            // 1. 벨트 회전
            int lastA = A[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                A[i] = A[i - 1];
            }
            A[0] = lastA;

            // 로봇도 회전
            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[N - 1] = false;

            // 2. 로봇 이동 (회전 후)
            for (int i = N - 2; i >= 0; i--) {
                if (robot[i] && !robot[i + 1] && A[i + 1] > 0) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    A[i + 1]--;
                }
            }
            robot[N - 1] = false;

            // 3. 로봇 올리기
            if (A[0] > 0) {
                robot[0] = true;
                A[0]--;
            }

            // 4. 내구도 0 개수 검사
            int cnt = 0;
            for (int x : A) {
                if (x == 0) cnt++;
            }
            if (cnt >= K) break;
        }

        System.out.println(result);
    }
}
