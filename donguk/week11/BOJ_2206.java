package donguk.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    static int [] dx , dy;
    static char[][] board;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(sz.nextToken());
        M = Integer.parseInt(sz.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        dx = new int[]{0, 0, 1, -1};
        dy = new int[]{1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] visited = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        // 0 -> 벽 아직 안부심 , 1 -> 벽 부술 수 있음

        queue.add(new int[]{0, 0, 1});
        visited[0][0][1] = 1;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int z = tmp[2];
            int value = visited[x][y][z];

            // 그냥 이동하기
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (visited[nx][ny][z] == -1 && board[nx][ny] == '0') {
                        visited[nx][ny][z] = value + 1;
                        queue.add(new int[]{nx, ny, z});
                    }
                }
            }

            // 벽 부수고 이동하기
            if (z == 1) {
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (visited[nx][ny][0] == -1 && board[nx][ny] == '1') {
                            visited[nx][ny][0] = value + 1;
                            queue.add(new int[]{nx, ny, 0});
                        }
                    }
                }
            }
        }

        int a = visited[N - 1][M - 1][0];
        int b = visited[N - 1][M - 1][1];

        if ( a == -1 && b == -1) System.out.println(-1);
        else if (a == -1) System.out.println(b);
        else if (b== -1) System.out.println(a);
        else System.out.println(Math.min(a,b));

    }


}
