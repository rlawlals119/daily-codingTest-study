package jeemin.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_22251 {
    static int K;
    static int N;
    static int P;
    static String X;
    static int[][] change;
    static int[] floar;
    static int[] maxN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        change = new int[][] {
                {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
                {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
                {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
                {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
                {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
                {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
                {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
                {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
                {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
                {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
        };

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt((st.nextToken()));   // 가장 높은 층
        K = Integer.parseInt(st.nextToken());   // 자리수
        P = Integer.parseInt(st.nextToken());   // 반전 가능 횟수
        X = st.nextToken();   // 현재 층

        floar = new int[K];    // 현재 층 (배열)
        for (int i = 0; i < X.length(); i++) {
            floar[K - X.length() + i] = Integer.parseInt(X.substring(i, i + 1));
        }
        DFS(floar, P, 0);
        System.out.println(ans);
    }
    static int ans = 0;
    static void DFS(int[] cur, int chance, int idx) {
        // 모든 자리의 숫자 변환 후 & 반전 1회이상 한 경우
        if (idx == K) {
            if (compare(cur)) {
                ans++;
            }
            return;
        }
        // idx 자리의 수를 i로 변환
        for (int i = 0; i <= 9; i++) {
            if (change[floar[idx]][i] <= chance) {  // 숫자 i로 변환 가능한 경우
                int[] f = cur.clone();
                f[idx] = i;
                DFS(f, chance - change[floar[idx]][i], idx + 1);
                f[idx] = cur[idx];  // 백트래킹
            }
        }
    }
    static boolean compare(int[] num) {
        int n = 0;

        for (int i = 0; i < K ; i++) {
            n += num[i] * (int) Math.pow(10, K - i - 1);
        }
        return n <= N && n > 0 && n != Integer.parseInt(X);
    }
}
