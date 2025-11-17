package donguk;
import java.util.*;

public class PG_보물지도 {
    public int solution(int n , int m , int [][]hole) {
        int answer = -1;
        int[][] board = new int[n][m];
        int[][][] visited = new int[n][m][2];
        Queue<int []> queue = new ArrayDeque <> ();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[] djx = {0, 0, 2, -2};
        int[] djy = {2, -2, 0, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }

        for (int[] h : hole) {
            int x = n - h[0];
            int y = h[1] -1;
            visited[x][y][0] = -2;
            visited[x][y][1] = -2;
        }

        queue.offer(new int[]{n-1,0,1});
        visited[n-1][0][1] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];
            int value = visited[x][y][z];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && 0 <= ny && ny < m && visited[nx][ny][z] == -1) {
                    visited[nx][ny][z] = value + 1;
                    queue.add(new int[]{nx,ny,z});
                }
            }

            if (z == 1) {
                for (int k = 0; k < 4; k++) {
                    int njx = djx[k] + x;
                    int njy = djy[k] + y;

                    if (njx >= 0 && njx < n && njy >= 0 && njy < m && visited[njx][njy][0] == -1) {
                        visited[njx][njy][0] = value + 1;
                        queue.add(new int[]{njx, njy, 0});
                    }
                }
            }

        }

        int a = visited[0][m - 1][0];
        int b = visited[0][m - 1][1];

        if (a >= 0 && b >= 0) {
            answer = Math.min(a, b);
        } else if (a>= 0 ) {
            answer = a;
        } else if (b >= 0) {
            answer = b;
        }

        return answer;

    }



    public static void main(String[] args) {
        PG_보물지도 sol = new PG_보물지도();
        System.out.println(sol.solution(4, 4, new int[][]{{2,3}, {3,3}}));
        System.out.println(sol.solution(5, 4, new int[][]{
                {1,4}, {2,1}, {2,2}, {2,3}, {2,4}, {3,3}, {4,1}, {4,3}, {5,3}
        }));

    }
}
