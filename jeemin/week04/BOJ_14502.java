package jeemin.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int ans = 0;

        List<int[]> virus = new ArrayList<>();
        List<int[]> walls = new ArrayList<>();

        String[][] board = new String[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                // 바이러스일 경우
                if (line[j].equals("2")) {
                    virus.add(new int[]{i, j});
                }
                // 빈 공간일 경우
                else if (line[j].equals("0")) {
                    walls.add(new int[]{i, j});
                }
            }
            board[i] = line;
        }


        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        // 벽 3개 세우기
        for (int i = 0; i < walls.size(); i++) {
            for (int j = i + 1; j < walls.size(); j++) {
                for (int k = j + 1; k < walls.size(); k++) {
                    int[] loc1 = walls.get(i);
                    int[] loc2 = walls.get(j);
                    int[] loc3 = walls.get(k);

                    board[loc1[0]][loc1[1]] = "1";
                    board[loc2[0]][loc2[1]] = "1";
                    board[loc3[0]][loc3[1]] = "1";

                    String[][] temp = new String[n][m];
                    for (int t = 0; t < n; t++) {
                        temp[t] = board[t].clone();
                    }

                    // 바이러스 퍼지는 BFS
                    Queue<int[]> q = new LinkedList<>();
                    for (int[] v : virus) {
                        q.offer(v);
                    }

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int x = cur[0] + dx[d];
                            int y = cur[1] + dy[d];

                            if (x >= 0 && x < n && y >= 0 && y < m && temp[x][y].equals("0") ) {
                                q.offer(new int[]{x, y});
                                temp[x][y] = "2";
                            }
                        }
                    }
                    int cnt = 0;
                    for (String[] t : temp) {
                        for (String s : t) {
                            if (s.equals("0")) {
                                cnt++;
                            }
                        }
                    }
                    ans = Math.max(ans, cnt);

                    board[loc1[0]][loc1[1]] = "0";
                    board[loc2[0]][loc2[1]] = "0";
                    board[loc3[0]][loc3[1]] = "0";
                }
            }
        }
        System.out.println(ans);
    }
}
