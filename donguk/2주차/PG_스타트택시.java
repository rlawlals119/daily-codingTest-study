package donguk;

import java.io.*;
import java.util.*;

public class PG_스타트택시 {

    static int n, m, gas;
    static int[][] board;
    static int carX, carY;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<Person> personList = new ArrayList<>();

    static class Person {
        int sx, sy;
        int gx, gy;
        int dist;

        Person(int sx, int sy, int gx, int gy) {
            this.sx = sx;
            this.sy = sy;
            this.gx = gx;
            this.gy = gy;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        // 0 = 길, 1 = 벽
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        carX = Integer.parseInt(st.nextToken()) - 1;
        carY = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken()) - 1;
            int py = Integer.parseInt(st.nextToken()) - 1;
            int gx = Integer.parseInt(st.nextToken()) - 1;
            int gy = Integer.parseInt(st.nextToken()) - 1;

            personList.add(new Person(px, py, gx, gy));
        }

        while (!personList.isEmpty()) {

            Person next = checkNextDistance();

            if (next == null || gas < next.dist) {
                System.out.println(-1);
                return;
            }


            gas -= next.dist;
            carX = next.sx;
            carY = next.sy;

            // 승객 목적지까지 이동
            int toGoal = getDistance(carX, carY, next.gx, next.gy);

            if (toGoal == -1 || gas < toGoal) {
                System.out.println(-1);
                return;
            }

            gas -= toGoal;
            gas += (toGoal * 2);

            carX = next.gx;
            carY = next.gy;

            personList.remove(next);
        }

        System.out.println(gas);
    }

    // BFS 로 가장 가까운 승객 찾기
    static Person checkNextDistance() {

        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int[] d : dist) Arrays.fill(d, -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{carX, carY});
        visited[carX][carY] = true;
        dist[carX][carY] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        ArrayList<Person> candidates = new ArrayList<>();

        for (Person p : personList) {
            int d = dist[p.sx][p.sy];
            if (d != -1) {
                p.dist = d;
                candidates.add(p);
            }
        }

        if (candidates.isEmpty()) return null;


        candidates.sort((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.sx != b.sx) return a.sx - b.sx;
            return a.sy - b.sy;
        });

        return candidates.get(0);
    }


    static int getDistance(int sx, int sy, int gx, int gy) {
        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int[] d : dist) Arrays.fill(d, -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (x == gx && y == gy) {
                return dist[x][y];
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return -1;
    }
}
