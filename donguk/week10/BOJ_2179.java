package donguk.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // 사전순 정렬용 인덱스 배열
        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> words[a].compareTo(words[b]));

        int maxLen = 0;
        for (int i = 0; i < N - 1; i++) {
            int len = calculate(words[idx[i]], words[idx[i + 1]]);
            maxLen = Math.max(maxLen, len);
        }

        /*  입력 순서가 가장 빠른 거 찾기 */
        int ans1 = 0, ans2 = 1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (calculate(words[i], words[j]) == maxLen) {
                    ans1 = i;
                    ans2 = j;
                    System.out.println(words[ans1]);
                    System.out.println(words[ans2]);
                    return;
                }
            }
        }
    }

    static int calculate(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }
}
