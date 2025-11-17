package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
// 0 : 이동가능 , 1 : 벽
public class BOJ_벽부수고이동하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        Deque<int[]> queue = new ArrayDeque<>();

        int N = Integer.parseInt(sz.nextToken());
        int M = Integer.parseInt(sz.nextToken());
        int answer = 0;

        int[][] board = new int[N][M];
        int[][][] visited = new int[N][M][2];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < N; i++) {
            String x = bf.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = x.charAt(j) -'0'; //✨✨✨✨✨이거 문자-> 숫자로 바꿔줌
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }

//        System.out.println("board = " + Arrays.deepToString(board));
//        System.out.println("visited = " + Arrays.deepToString(visited));

        queue.offer(new int[]{0, 0, 1});
        visited[0][0][1] = 0;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int z = tmp[2];
            int value = visited[x][y][z];

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M && visited[nx][ny][z] == -1 && board[nx][ny] == 0) {
                    visited[nx][ny][z] = value + 1;
                    queue.add(new int[]{nx, ny, z});
                }

                if (0 <= nx && nx < N && 0 <= ny && ny < M && z == 1 && board[nx][ny] == 1 && visited[nx][ny][0] == -1) {
                    visited[nx][ny][0] = value + 1;
                    queue.add(new int[]{nx, ny, 0});
                }
            }

        }

        int x = visited[N - 1][M - 1][1];
        int y = visited[N - 1][M - 1][0];

        if (x == -1 && y == -1) {
            answer = -1;
        } else if (x == -1) {
            answer = y + 1;
        } else if (y == -1) {
            answer = x + 1;
        } else {
            answer = Math.min(x, y);
        }

        System.out.println("answer = " + answer);
    }

    
}
