package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int K = Integer.parseInt(sz.nextToken());

        int[] visited = new int[200001];
        Arrays.fill(visited, -1);

        Queue<Integer> queue = new ArrayDeque<>();

        visited[N] = 0;
        queue.offer(N);

        while(!queue.isEmpty()){
            int x = queue.poll();
            int value = visited[x];

            int[] next = {x - 1, x + 1};

            int y = x * 2;
            if (0 <= y && y < 200001 && visited[y] == -1) {
                visited[y] = value;
                queue.offer(y);
            }

            for (int y2 : next) {
                if (0 <= y2 && y2 < 200001 && visited[y2] == -1) {
                    visited[y2] = value + 1;
                    queue.offer(y2);
                }
            }


        }

        System.out.println(visited[K]);
    }
}
