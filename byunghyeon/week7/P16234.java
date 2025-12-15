package byunghyeon.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16234 {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N, L, R;
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        boolean move = false;
        do {
            visited = new boolean[N][N];
            // BFS로 연합들을 찾는다.
            move = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if(findUnity(i, j))
                            move=true;
                    }
                }
            }

            if(move) day++;
        } while(move);
        // 카운트 업데이트
        // 반복

        System.out.println(day);
    }

    static public boolean findUnity(int sx, int sy){
        Queue<int []> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        List<int []> xyList = new ArrayList<>();
        xyList.add(new int[]{sx, sy});
        int unitySum = map[sx][sy];
        int unityNum = 1;

        boolean move = false;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            // 1. 연합 모으기
            for(int i = 0; i < 4; i++){
                int newX = cur[0] + dx[i];
                int newY = cur[1] + dy[i];

                if(newX >= 0 && newX < N && newY >= 0 && newY < N){
                    if(!visited[newX][newY]){
                        int next = map[newX][newY];
                        int diff = Math.abs(next - map[cur[0]][cur[1]]);
                        if(diff >= L && diff <= R){
                            visited[newX][newY] = true;
                            move = true;

                            xyList.add(new int[]{newX, newY});
                            unitySum+=next;
                            unityNum++;

                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            } // for: 좌표이동
        }

        // 2. 업데이트
        if(unityNum > 1) {
            int population = unitySum / unityNum;
            for (int[] country : xyList) {
                map[country[0]][country[1]] = population;
            }
        }

        return move;
    }
}
