package jeemin.week03;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[Math.max(n, k) * 2];
        q.offer(new int[]{n, 0});
        visited[n] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == k) {
                System.out.println(cur[1]);
                break;
            }

            if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
                q.offer(new int[]{cur[0] - 1, cur[1] + 1});
                visited[cur[0] - 1] = true;
            }
            if (cur[0] + 1 < visited.length && !visited[cur[0] + 1]) {
                q.offer(new int[]{cur[0] + 1, cur[1] + 1});
                visited[cur[0] + 1] = true;
            }
            if (cur[0] * 2 < visited.length && !visited[cur[0] * 2]) {
                q.offer(new int[]{cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = true;
            }
        }
    }
}
