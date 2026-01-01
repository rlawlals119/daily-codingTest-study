package donguk.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4179 {
    static int R , C;
    static int[] dx;
    static int[] dy;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(sz.nextToken());
        C = Integer.parseInt(sz.nextToken());
        board = new char[R][C];
        dx = new int[]{0, 0, 1, -1};
        dy = new int[]{1, -1, 0, 0};

        for (int i = 0; i < R; i++) {
            String line = bf.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        ArrayList<int[]> fireStart = new ArrayList<>();
        int jeeX = -1, jeeY = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'F') {
                    fireStart.add(new int[]{i, j});
                } else if (board[i][j] == 'J') {
                    jeeX = i;
                    jeeY = j;
                }
            }
        }

        int[][] fireResult = fireBfs(fireStart);

        jeeBfs(jeeX, jeeY, fireResult);


    }

    static void jeeBfs(int startX, int startY, int[][] fireResult) {
        int[][] visited = new int[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(visited[i], -1);

        Queue<int[]> queue = new ArrayDeque<>();
        visited[startX][startY] = 0;
        queue.offer(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int time = visited[x][y];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    System.out.println(time + 1);
                    return;
                }

                if (visited[nx][ny] != -1) continue;
                if (board[nx][ny] == '#') continue;

                if (fireResult[nx][ny] != -1 && time + 1 >= fireResult[nx][ny]) continue;

                visited[nx][ny] = time + 1;
                queue.offer(new int[]{nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    static int[][] fireBfs (ArrayList<int[]> start) {
        int[][] visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(visited[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int[] sample : start) {
            int startX = sample[0];
            int startY = sample[1];

            visited[startX][startY] = 0;
            queue.offer(new int[]{startX, startY});
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int value = visited[x][y];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                    if (visited[nx][ny] == -1 && board[nx][ny] != '#') {
                        visited[nx][ny] = value + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }

        }
        return visited;
    }
}
