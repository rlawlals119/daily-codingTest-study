package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14718 {
    static boolean[] visited;
    static int[][] soldiers;
    static int N,K;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 병사 수
        K = Integer.parseInt(st.nextToken()); // 최소 이겨야하는 수

        soldiers = new int[N][3];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            soldiers[i][0] = Integer.parseInt(st.nextToken());
            soldiers[i][1] = Integer.parseInt(st.nextToken());
            soldiers[i][2] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        battle(0,0,0,0, 0);
        System.out.println(min);
    }

    public static void battle(int a, int b, int c, int stage, int count){
        if(min <= a+b+c) return;
        if(count + (N-stage) < K) return;

        if(count >= K){
            min = Math.min(a+b+c, min);
            return;
        }

        for(int i = stage; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                battle(Math.max(a,soldiers[i][0]), Math.max(b,soldiers[i][1]), Math.max(c,soldiers[i][2]), i+1, count+1);
                visited[i] = false;
            }
        }
    }


}
