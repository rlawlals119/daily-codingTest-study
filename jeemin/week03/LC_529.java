package jeemin.week03;
import java.util.*;

public class LC_529 {
    static int m;
    static int n;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // 첫 클릭이 "M"인 경우
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        q.offer(new int[]{click[0], click[1]});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            // E일 경우 주변에 mine 있는지 확인, "B"이면 주변 q에 추가
            if (board[cur[0]][cur[1]] == 'E') {
                int mineN = findMine(cur[0], cur[1], board);
                if (mineN == 0) {
                    board[cur[0]][cur[1]] = 'B';
                    for (int i = 0; i < 8; i++) {
                        int x = dx[i] + cur[0];
                        int y = dy[i] + cur[1];

                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            q.offer(new int[]{x, y});
                        }
                    }
                }
                else board[cur[0]][cur[1]] = Character.forDigit(mineN, 10);
            }
        }
        return board;
    }

    static int findMine(int a, int b, char[][] board) {
        int mine = 0;
        for (int i = 0; i < 8; i++) {
            int x = dx[i] + a;
            int y = dy[i] + b;
            // 주변에 지뢰가 있는 경우
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'M') {
                mine++;
            }
        }
        return mine;
    }
}
