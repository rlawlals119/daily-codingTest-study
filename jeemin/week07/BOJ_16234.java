package jeemin.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234 {
    static int N;
    static int L;
    static int R;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] pop = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pop[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        while (move(pop)) {
            ans++;
            List<List<int[]>> unions = new ArrayList<>();    // 여러 연합의 나라 인덱스
            List<Integer> unions_pop = new ArrayList<>();    // 각 연합의 총 인구수들
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) {
                        boolean flag = false;   // 해당 위치 주변에 국경선 공유할 나라가 있는지 확인
                        int popSum= 0;    //  한 연합의 총 인구
                        List<int[]> union = new ArrayList<>();  // 한 연합인 나라들의 인덱스
                        Queue<int[]> q = new LinkedList<>();
                        q.add(new int[]{i, j});
                        visited[i][j] = true;
                        while (!q.isEmpty()) {  // 국경선 공유하는 나라 찾기
                            int[] cur = q.poll();
                            popSum += pop[cur[0]][cur[1]];   // 같은 국경선 인구수 추가
                            union.add(cur);

                            for (int d = 0; d < 4; d++) {
                                int x = cur[0] + dx[d];
                                int y = cur[1] + dy[d];
                                if (x >= 0 && x < N && y >= 0 && y < N && visited[x][y] == false) {
                                    int diff = Math.abs(pop[cur[0]][cur[1]] - pop[x][y]);
                                    if (diff >= L && diff <= R) {
                                        flag = true;
                                        visited[x][y] = true;
                                        q.add(new int[]{x, y});
                                    }
                                }
                            }
                            if (!flag) {break;}
                        }
                        if (flag) {unions.add(union); unions_pop.add(popSum);}
                    }
                }
            }
            // 인구수 업데이트
            for (int i = 0; i < unions.size(); i++) {
                int avg = unions_pop.get(i) / unions.get(i).size();
                for (int[] u : unions.get(i)) {
                    pop[u[0]][u[1]] = avg;
                }
            }
        }

        System.out.println(ans);
    }

    static boolean move(int[][] pop) {  // 인구 이동 가능 여부 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < N && y >= 0 && y < N) {
                        int diff = Math.abs(pop[x][y] - pop[i][j]);
                        if (diff >= L && diff <= R) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
