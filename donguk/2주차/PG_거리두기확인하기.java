package donguk;

import java.util.*;

public class PG_거리두기확인하기 {
    public int[] solution(String[][] places) {
        int[] answer = new int [5];

        for (int i = 0; i < 5; i++) {
            String []place = places[i];
            boolean flag = true;

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (place[j].charAt(k) == 'P') {
                        boolean tmp = bfs(place, j, k);
                        if (!tmp) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag) break;
            }
            if (flag) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }

        }
        return answer;
    }

    public boolean bfs(String[] place, int start_x, int start_y) { // 🔹 place 타입도 1차원으로 수정해야 charAt 사용 가능
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        int[][] visited = new int[5][5];

        for (int i = 0; i < 5; i++) {
            Arrays.fill(visited[i], -1);
        }

        queue.offer(new int[]{start_x, start_y});
        visited[start_x][start_y] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int curDis = visited[x][y];

            //✨✨✨✨✨✨✨✨✨✨ 2까지만 탐색 ✨✨✨✨✨✨✨
            if (curDis == 2) continue;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && visited[nx][ny] == -1) {

                    char c = place[nx].charAt(ny);

                    if (c == 'X') continue; // ✨✨✨✨✨✨✨파티션은 벽처럼 통과 금지✨✨✨✨✨✨✨

                    if (curDis == 0 && c == 'P') {
                        return false;
                    } else if (curDis == 0 && c == 'O') {
                        visited[nx][ny] = curDis + 1;
                        queue.offer(new int[]{nx, ny});
                    } else if (curDis == 1 && c == 'P') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        PG_거리두기확인하기 s = new PG_거리두기확인하기();
        System.out.println(Arrays.toString(s.solution(places)));

    }
}
