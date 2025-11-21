package jeemin.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206 {
    public static void main(String[] args) throws IOException {
        int answer = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // 맵 입력
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> q = new LinkedList<>();
        // 위치, 벽 부순 횟수, 거리
        q.offer(new int[]{0, 0, 0, 1});
        boolean[][][] visited  = new boolean[n][m][2];
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            // 목적지에 도착한 경우
            if (cur[0] == n - 1 && cur[1] == m - 1) {
                System.out.println(cur[3]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];

                if (x >= 0 && x < n && y >= 0 && y < m) {
                    // 벽이 아닌 경우
                    if (map[x][y] == '0' && !visited[x][y][cur[2]]) {
                        q.offer(new int[]{x, y, cur[2], cur[3] + 1});
                        visited[x][y][cur[2]] = true;
                    }
                    // 벽인 경우
                    if (map[x][y] == '1' && cur[2] == 0 && !visited[x][y][cur[2] + 1]) {
                        q.offer(new int[]{x, y, cur[2] + 1, cur[3] + 1});
                        visited[x][y][cur[2] + 1] = true;
                    }
                }
            }

        }
        System.out.println(answer);
        return;
    }
}
