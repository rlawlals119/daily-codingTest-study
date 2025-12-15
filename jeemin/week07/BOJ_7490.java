package jeemin.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_7490 {
    static List<String> result;
    static int N;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            num = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                num[i] = i;
            }
            result = new ArrayList<String>();
            DFS(2, Integer.toString(num[1]));
            boolean plus = false;
            boolean minus = false;
            boolean blank = false;
            int curN = 0;
            for (String s : result) {
                int sum = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    // 숫자인 경우
                    if (c != '+' && c != '-' && c != ' ') {
                        if (blank) {curN = curN * 10 + (c - '0'); blank = false;}    // 앞에 빈칸이 있던 경우에는 숫자 업데이트
                        else {curN = c - '0';}
                    }

                    if (c == '+' || c == '-' || i == s.length() - 1) {  // 다음 수식 기호가 나왔을 때 또는 수식이 끝났을 때 이전의 수식 기호 처리
                        if (!plus && !minus) {sum = curN; curN = 0;}  // 첫 숫자일 경우 sum에 넣기

                        if (plus) {sum += curN; curN = 0; plus = false;}
                        if (minus) {sum -= curN; curN = 0; minus = false;}

                        if (c == '+') plus = true;
                        if (c == '-') minus = true;
                    }
                    if (c == ' ') {blank = true;}
                }
                if (sum == 0) { // 수식의 결과가 0인 경우 출력
                    System.out.println(s);
                }
            }
//            System.out.print("\n");
        }
    }

    static void DFS(int idx, String s) {
        if (idx == N + 1) { // 수식이 완성된 경우 result에 추가후 return
            result.add(s);
            return;
        }

        DFS(idx + 1, s + " " + Integer.toString(num[idx]));
        DFS(idx + 1, s + "+" + Integer.toString(num[idx]));
        DFS(idx + 1, s + "-" + Integer.toString(num[idx]));
    }
}
