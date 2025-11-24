package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_구슬탈출 {
    static int N , M;
    static char[][] board;
    static int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; //왼쪽,오른쪽,위,아래
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(sz.nextToken());
        M = Integer.parseInt(sz.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        System.out.println(Arrays.deepToString(board));

        int[] R = getBall('R');
        int[] B = getBall('B');

        System.out.println("Arrays.toString(R) = " + Arrays.toString(R));
        System.out.println("Arrays.toString(B) = " + Arrays.toString(B));
    }

    // 최단거리 = bfs
    // 경로를 탐색하는게 아니라 최단 횟수를 탐색한다
    // 즉 bfs 는 보드 칸을 탐색하는게 아니라 상태 를 탐색한다
    static int solution(int[]R , int[]B) {

    }

    //0 : 왼쪽, 1 : 오른쪽, 2: 위, 3: 아래
    static void tilt (int[] R , int [] B , int dir) {
        boolean redFirst = false;
        boolean blueFirst = false;

        // 왼쪽
        if (dir == 0) {
            if(R[1]<B[1])redFirst = true;

        //오른쪽
        } else if (dir == 1) {
            if(R[1]>B[1]) redFirst = true;
        //위
        } else if (dir == 2) {
            if(R[0]<B[0]) redFirst = true;
        //아래
        } else {
            if(R[0]>B[0]) redFirst = true;
        }

        if (redFirst) {
            move('R', R, B, dir);
            move('B', R, B, dir);
        } else {
            move('B', R, B, dir);
            move('R', R, B, dir);
        }

        if (R[0] == B[0] && R[1] == B[1]) {
            if (redFirst) {
                B[0] -= dirs[dir][0];
                B[1] -= dirs[dir][1];
            } else {
                R[0] -= dirs[dir][0];
                R[1] -= dirs[dir][1];
            }
        }

    }



    /*
         현재 움직이는 공(cur) 을 움직인다
         1. 벽을 만나거나
         2.다른공을 만나거나
         3.구멍을 만나기
         4. 빈칸
      */
    //특정 방향으로 공을 굴리는 함수
    static void move(char cur , int[] R , int[] B, int dir) {
        int currR, currC, anotherR, anotherC;
        if (cur == 'R') {
            currR = R[0]; currC = R[1];
            anotherR = B[0]; anotherC = B[1];
        } else {
            currR = B[0]; currC = B[1];
            anotherR = R[0]; anotherC = R[1];
        }

        while (true) {
            int nr = currR + dirs[dir][0];
            int nc = currC + dirs[dir][1];

            // 다른 공을 만난 경우 (2번)
            if (nr == anotherR && nc == anotherC) {
                break;
            }
            //빈칸을 만난 경우 (4번)
            else if (board[nr][nc] == '.') {
                currR = nr;
                currC = nc;
            }
            // 벽을 만난 경우 (1번)
            else if (board[nr][nc] == '#') {
                break;
            }
            // 구멍을 만난 경우
            else {
                continue;
            }

            if (cur == 'R') {
                R[0] = currR;
                R[1] = currC;
            } else {
                B[0] = currR;
                B[1] = currC;
            }




        }
    }

    static public int[] getBall(char ball) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == ball) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
