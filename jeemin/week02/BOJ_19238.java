package jeemin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19238 {
    static int n;
    static int m;
    static int[][] map;
    static int[] start;
    static int[][][] dest;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        dest = new int[n][n][2];

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        // 택시 시작 위치
        start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        // 승객 위치
        for (int i = 0; i < n; i++) {   // -1로 초기화
            for (int j = 0; j < n; j++) {
                dest[i][j][0] = -1;
                dest[i][j][1] = -1;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            dest[a][b][0] = c;
            dest[a][b][1] = d;
        }

        int answer = BFS(start, fuel);
        System.out.println(answer);
        return;
    }

    // 상좌우하 탐색
    static int[][] dirct = {
            {-1, 0},
            {0, -1},
            {0, 1},
            {1, 0},
    };

    // 승객찾는 BFS
    public static int BFS(int[] start, int fuel) {
        int[][] visited = new int[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1], fuel});
        visited[start[0]][start[1]] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int[] loc = new int[]{now[0], now[1]};
            // 연료 없을 경우 패스
            if (now[2] < 0) return -1;
            // 승객 위치일 경우 목적지까지 거리 찾기
            if (dest[loc[0]][loc[1]][0] != -1) {
                findDist(loc, dest[loc[0]][loc[1]], now[2]);
                return fuel;
            }

            for (int[] dir : dirct) {
                int x = now[0] + dir[0];
                int y = now[1] + dir[1];
                // 방문한적 없는 경우 탐색
                if (x >= 0 && x < n && y >= 0 && y < m && map[x][y] == 0 && visited[x][y] == 0) {
                    q.add(new int[]{x, y, now[2] - 1});
                }
            }
        }
    }

    // 목적지 찾는 BFS
    public static void findDist(int[] start, int[] end, int fuel) {
        int[][] visited = new int[n][n];
        Queue<int[]> q2 = new LinkedList<>();
        // 시작 행, 시작 열, 거리
        q2.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = 1;
        while (!q2.isEmpty()) {
            int[] now = q2.poll();
            int[] loc = new int[]{now[0], now[1]};
            // 연료 부족시
            if (now[2] < 0) break;
            // 목적지 도달시
            if (Arrays.equals(loc, end)) {
                BFS(loc, fuel + now[2] * 2);
                break;
            }

            for (int[] dir : dirct) {
                int x = now[0] + dir[0];
                int y = now[1] + dir[1];

                if (x >= 0 && x < n && y >= 0 && y < m && visited[x][y] == 0) {
                    q2.add(new int[]{x, y, now[2] - 1});
                }
            }
        }
    }
}
