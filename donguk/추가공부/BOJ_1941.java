package donguk.추가공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
    1. 25개중 7개 뽑아
    2. 뽑은 7개의 좌표에서 Y가 4이상이면 컷
    3. 7개의 값이 상하 좌우로 연결 되어 있는지 검사
 */
public class BOJ_1941 {
    static char[][] board;
    static int[][] map;
    static int[] b;
    static int[] a;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String line = bf.readLine();
            for (int j = 0; j < 5; j++) {
                char c = line.charAt(j);
                board[i][j] = c;
            }
        }

        //0~25 좌표 넣기
        map = new int[][]{{0,0},{0,1},{0,2},{0,3},{0,4}
                                 ,{1,0},{1,1},{1,2},{1,3},{1,4}
                                 ,{2,0},{2,1},{2,2},{2,3},{2,4}
                                 ,{3,0},{3,1},{3,2},{3,3},{3,4}
                                 ,{4,0},{4,1},{4,2},{4,3},{4,4}};
        b = new int[7];
        a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};

        //25개중 7개 뽑기
        comb(0, 0);
        System.out.println(result);
    }
    static void comb(int cnt , int start) {
        if(cnt == 7) {
//            System.out.println(Arrays.toString(b));

//            뽑은거 검사
            if(!check(b)) return;
            boolean flag = bfs(b);
            if(flag) result++;
            return;
        };

        for (int i = start; i < 25; i++) {
            b[cnt] = a[i];
            comb(cnt + 1, i + 1);
        }
    }

    static boolean check(int[] tmp) {
        int yCnt = 0;

        for (int checkNum : tmp) {
            int x = map[checkNum][0];
            int y = map[checkNum][1];

            if (board[x][y] == 'Y') yCnt++;
        }

        if(yCnt>=4) return false;
        return true;
    }

    static boolean bfs(int[]tmp) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        boolean[][] selected = new boolean[5][5];

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int checkNum : tmp) {
            int x = map[checkNum][0];
            int y = map[checkNum][1];
            selected[x][y] = true;

        }

        int x = map[tmp[0]][0];
        int y = map[tmp[0]][1];
        int cnt = 1;

        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int [] curr = queue.poll();
            int sx = curr[0];
            int sy = curr[1];

            for (int k = 0; k < 4; k++) {
                int nx = sx + dx[k];
                int ny = sy + dy[k];

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && !visited[nx][ny]) {
                    if (selected[nx][ny]) {
                        cnt++;
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }


        }
        return cnt == 7;

    }
}
