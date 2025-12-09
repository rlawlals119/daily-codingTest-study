package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P14718_2 {
    static int[][] soldiers;
    static int N,K;

    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 병사 수
        K = Integer.parseInt(st.nextToken()); // 최소 이겨야하는 수

        soldiers = new int[N][3];
        int[] al = new int[N];
        int[] bl = new int[N];
        int[] cl = new int[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            soldiers[i][0] = a;
            soldiers[i][1] = b;
            soldiers[i][2] = c;
            al[i] = a;
            bl[i] = b;
            cl[i] = c;
        }
        // 좌표 압축을 이용한 bruth-force
        int answer = Integer.MAX_VALUE;
        for (int i : al) {
            for (int j : bl) {
                for (int k : cl) {

                    int cnt = 0;
                    for(int h = 0; h < N; h++){
                        if(soldiers[h][0] <= i && soldiers[h][1] <= j && soldiers[h][2] <= k)
                            cnt++;
                    }

                    if(cnt == K){
                        answer = Math.min(i+j+k, answer);
                    }
                }
            }
        }

        System.out.println(answer);

    }

}
