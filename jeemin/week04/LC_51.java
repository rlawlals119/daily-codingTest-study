package jeemin.week04;

import java.util.*;
class LC_51 {
    // 상, 대각선 왼쪽위 오른쪽위
    static int[] dx = new int[]{-1, -1, -1};
    static int[] dy = new int[]{0, -1, 1};
    static List<String[][]> ans;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        String[][] board = new String[n][n];
        for (String[] b : board) {
            Arrays.fill(b, ".");
        }

        ans = new ArrayList<>();
        // 첫 Q 정하기, DFS 실행
        for (int i = 0; i < n; i++) {
            board[0][i] = "Q";
            dfs(0, i, n, board);
//            // n == 1일때를 위한
//            if(i == n - 1) break;
            board[0][i] = ".";
        }

        for (String[][] ans1 : ans) {
            List<String> list = new ArrayList<>();
            for (String[] ans2 : ans1) {
                list.add(String.join("", ans2));
            }
            answer.add(list);
        }

        return answer;
    }

    static void dfs(int a, int b, int n, String[][] board) {
        if (a == n - 1) {
            ans.add(board);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (checkQ(a + 1, i, n, board)) {
                String[][] tempt = new String[n][n];
                for (int j = 0; j < n; j++) {
                    tempt[j] = board[j].clone();
                }
                tempt[a + 1][i] = "Q";
                dfs(a + 1, i, n, tempt);
            }
        }
    }

    static boolean checkQ(int a, int b, int n, String[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= a; j++) {
                int x = a + dx[i] * j;
                int y = b + dy[i] * j;

                if (x >= 0 && x < n && y >= 0 && y < n && arr[x][y].equals("Q")) return false;
            }
        }
        return true;
    }
}