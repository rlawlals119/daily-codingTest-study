package donguk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_NQueens {
    List<List<String>> answer;
    int n;
    StringBuilder tmp;

    //수직
    boolean[] check1;
    //대각선1
    boolean[]check2;
    //대각선2
    boolean[]check3;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        tmp = new StringBuilder();
        answer = new ArrayList<>();
        check1 = new boolean[n];
        check2 = new boolean[n*2];
        check3 = new boolean[n*2];

        dfs(0);
        return answer;
    }

    public void dfs(int col) {

        if (col == n) {
            List<String> board = new ArrayList<>();//⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
            for (int i = 0; i < n; i++) {
                int start = i * n;//⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
                board.add(tmp.substring(start, start + n));//⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
            }
            answer.add(board);
            return;
        }


        for (int i = 0; i < n; i++) {
            int x = i+col;
            int y = i - col + (n - 1); //⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐

            if(check1[i] || check2[x] || check3[y]) continue;

            check1[i] = true;
            check2[x] = true;
            check3[y] = true;

            tmp.append(".".repeat(i))
                    .append("Q")
                    .append(".".repeat(n - i - 1));

            dfs(col + 1);

            check1[i] = false;
            check2[x] = false;
            check3[y] = false;

            tmp.delete(tmp.length() - n, tmp.length()); //⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
        }
    }


    public static void main(String[] args) {
        LC_NQueens solve = new LC_NQueens();
        List<List<String>> list = solve.solveNQueens(4);
        List<List<String>> list2 = solve.solveNQueens(1);
        System.out.println(Arrays.deepToString(list.toArray()));
        System.out.println(Arrays.deepToString(list2.toArray()));
    }
}
