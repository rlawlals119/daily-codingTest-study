package donguk.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1987 {
    static int[] dx , dy;
    static int R , C , result;
    static char[][] board;
    static HashSet<Character> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(sz.nextToken());
        C = Integer.parseInt(sz.nextToken());
        board = new char[R][C];
        set = new HashSet<>();
        dx = new int[]{0, 0, 1, -1};
        dy = new int[]{1, -1, 0, 0};
        result=Integer.MIN_VALUE;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 1);
        System.out.println(result);

    }

    static void dfs(int x , int y , int depth) {
//        종료조건
        if (result < depth) {
            result = depth;
        }

        set.add(board[x][y]);

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if( 0<= nx && nx < R && 0<= ny && ny < C ) {
                char next =  board[nx][ny];
                if(set.contains(next)) continue;
                dfs(nx, ny, depth + 1);
            }
        }
        set.remove(board[x][y]);
    }
}
