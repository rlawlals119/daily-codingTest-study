package jeemin.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_7682 {
    static int oNum;
    static int xNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        List<String[]> boards = new ArrayList<>();
        while (true) {
            String[] s = br.readLine().split("");

            if (s[0].equals("e")) {
                break;
            }
            // O, X 갯수 세기
            oNum = 0;
            xNum = 0;
            for (String str : s) {
                if (str.equals("X")) {
                    xNum++;
                }
                if (str.equals("O")) {
                    oNum++;
                }
            }

            if (oNum > xNum || xNum - oNum > 1) {
                System.out.println("invalid");
                continue;
            }
            if (TTT(s)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }
    static boolean TTT(String[] board) {
        // 가로, 세로 성공 확인
        boolean oWin = false;
        boolean xWin = false;
        for (int i = 0; i < 3; i++) {
            // 가로
            if (board[i * 3].equals(board[i * 3 + 1]) && board[i * 3].equals(board[i * 3 + 2])) {
                if (board[i * 3].equals("X")) xWin = true;
                else if (board[i * 3].equals("O")) oWin = true;
            }
            // 세로
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                if (board[i].equals("X")) xWin = true;
                else if (board[i].equals("O")) oWin = true;
            }
        }
        // 대각선 성공 확인
        if (board[0].equals(board[4]) && board[0].equals(board[8])) {
            if (board[0].equals("X")) xWin = true;
            else if (board[0].equals("O")) oWin = true;
        }
        else if (board[2].equals(board[4]) && board[2].equals(board[6])) {
            if (board[2].equals("X")) xWin = true;
            else if (board[2].equals("O")) oWin = true;
        }

        // 둘다 승리
        if (oWin && xWin) {return false;}

        // x가 승리면 o보다 1 많아야 함
        if (!oWin && xWin && xNum - 1 != oNum) {return false;}

        // o가 승리면 x랑 o랑 개수 같아야 함
        if (oWin && !xWin && oNum != xNum) {return false;}

        // 승리가 없으면 꽉채워야 함
        if (!oWin && !xWin && xNum + oNum != 9) {return false;}

        return true;
    }
}


