package donguk.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
한번 시행 할때 0 번부터 끝까지 한칸 한칸 다 bfs 해야하네
 */
public class BOJ_16234 {
    static int N , L, R , result;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(br.readLine());

         N = Integer.parseInt(sz.nextToken());
         L = Integer.parseInt(sz.nextToken());
         R = Integer.parseInt(sz.nextToken());

        list = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < N; i++) {
            StringTokenizer sz2 = new StringTokenizer(br.readLine());
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                tmp.add(Integer.parseInt(sz2.nextToken()));
            }
            list.add(tmp);
        }
//        System.out.println(list);
//        System.out.println(Arrays.deepToString(visited));

//        bfs();
//        System.out.println(list);
        solution();
        System.out.println(result);

    }
    private static void solution() {

        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if(bfs(i,j,visited)){
                            flag = true;
                        }
                    }
                }
            }

            if(!flag) break;
            result++;
        }
    }

    // bfs 는 그냥 탐색 용도로만 !! 근데 간곳을 또 방문할 필요가 있을까 ? 그럴 필요 없을듯 !!
    private static boolean bfs(int startX , int startY,boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        ArrayList<int[]> union = new ArrayList<>();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        union.add(new int[]{startX,startY});

        int total = list.get(startX).get(startY);

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false) {
                    int diff = Math.abs(list.get(x).get(y) - list.get(nx).get(ny));

                    if (diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx,ny});
                        union.add(new int[]{nx,ny});
                        total+=list.get(nx).get(ny);
                    }
                }
            }
        }

        if (union.size() == 1) return false;

        int newValue = total / union.size();

        for(int[] tmp : union) {
            int unionX = tmp[0];
            int unionY = tmp[1];

            list.get(unionX).set(unionY,newValue);
        }

        return true;
    }
}
