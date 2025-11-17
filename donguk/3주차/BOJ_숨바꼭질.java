package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class BOJ_숨바꼭질  {

    public static void main(String[] args) throws IOException{
        final int MAX = 200001;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(sz.nextToken());
        int K = Integer.parseInt(sz.nextToken());
        Queue<Integer> queue = new ArrayDeque<>();
        Integer[] visited = new Integer[MAX];

        for (int i = 0; i < 200001; i++) {
            visited[i] = -1;
        }

        queue.offer(N);
        visited[N] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int value = visited[x];
            int[] moves = {x * 2, x + 1, x - 1};
            for (int next : moves) {
                if (0 <= next && next < MAX && visited[next] == -1) {
                    visited[next] = value + 1;
                    queue.offer(next);
                }
            }

        }

        System.out.println("visited[K] = " + visited[K]);

    }

}
