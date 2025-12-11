package jeemin.week06;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_14718 {
    static int[][] heros;
    static int n;
    static int k;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        // 용사 정보 입력
        heros = new int[n][3];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                heros[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int w = 0; w < n; ++w) {
                    int cnt = 0;
                    for (int[] h : heros) {
                        if (heros[i][0] >= h[0] && heros[j][1] >= h[1] && heros[w][2] >= h[2]) {
                            cnt++;
                            if (cnt == k) {
                                ans = Math.min(ans, heros[i][0] + heros[j][1] + heros[w][2]);
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);


    }
}
