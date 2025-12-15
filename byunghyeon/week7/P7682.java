package byunghyeon.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        while(true){
            String line = br.readLine();
            if(line.equals("end")) break;

            char[] board = line.toCharArray();

            int x = 0;
            int o = 0;
            for(int i = 0; i < 9; i++){
                char c = board[i];

                if(c == 'X') x++;
                else if(c == 'O') o++;
            }

            // [1.사전 검토] X와 O 개수 안 맞는 경우
            if(x != o+1 && x != o){
                answer.append("invalid").append("\n");
                continue;
            }

            int xBingo = checkBingo(board, 'X');
            int oBingo = checkBingo(board, 'O');
            // [2. 맵을 다 안 채우는 경우]
            if(o+x != 9) {
                // [2-1. X가 승리한 경우]
                if (x == o + 1 && xBingo == 1 && oBingo == 0) {
                    answer.append("valid").append("\n");
                    continue;
                }
                // [2-2. O가 승리한 경우]
                if (x == o && xBingo == 0 && oBingo == 1) {
                    answer.append("valid").append("\n");
                    continue;
                }
            }else if(x+o == 9){
                // [3. 맵을 다 채우는 경우]
                // [3-1. X가 승리한 경우]
                if (x == o + 1 && xBingo >= 1 && oBingo == 0) {
                    answer.append("valid").append("\n");
                    continue;
                }
                // [3-2. 승자가 없는 경우]
                if (x == o + 1 && xBingo == 0 && oBingo == 0) {
                    answer.append("valid").append("\n");
                    continue;
                }
            }

            answer.append("invalid").append("\n");
        }

        System.out.print(answer);
    }

    static public int checkBingo(char[] board, char player){
        int bingo = 0;
        // 가로 빙고
        if(player == board[0] && player == board[1] && player == board[2]) bingo++;
        if(player == board[3] && player == board[4] && player == board[5]) bingo++;
        if(player == board[6] && player == board[7] && player == board[8]) bingo++;
        // 세로 빙고
        if(player == board[0] && player == board[3] && player == board[6]) bingo++;
        if(player == board[1] && player == board[4] && player == board[7]) bingo++;
        if(player == board[2] && player == board[5] && player == board[8]) bingo++;
        // 대각선 빙고
        if(player == board[0] && player == board[4] && player == board[8]) bingo++;
        if(player == board[2] && player == board[4] && player == board[6]) bingo++;
        return bingo;
    }
}
