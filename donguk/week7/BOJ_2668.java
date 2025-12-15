package donguk.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



/*
<dfs 로 사이클을 찾는 방법>
1.dfs로 탐색을 하다 방문한 적이 없는 노드 -> 계속 탐색
2.dfs 로 탐색을 하다 방문한 적이 있는 노드
  2.1 dfs 가 계속 진행중이다 -> 사이클 안에 있는것
visited = true , finished = false : 사이클 안에 있는것
visited = true , finished = true : 이미 탐색이 끝남
각 노드 마다 visited , finished 여부를 통해 발견한다
 */

/* ⭐ 정리 ⭐
 1. 방문 한적 없으면 (visited[i]=false) 그냥 bfs 탐색
 2. 일반 bfs 를 돌다가 특이한 애 (cycle) 이 발견 됐을떄를 거르면 된다 base는 일반 bfs 돌기!!!
    -> bfs 돌리다가 이상한애 있으면 check
 3. 그럼 이상한 애를 어떻게 판단할꺼냐 ? visited = true 고 finished = false 면 cycle !!
    -> bfs 탐색이 안끝났는데(finished = false) 방문안 해를 또 방문함 (visited = true)
 4. 원래 bfs는 탐색이 끝났으면 그냥 return 해야하지만 특이한애(cycle)을 찾아내기 위해서 finished = true 로 명시하고 return 해야함
 */
public class BOJ_2668{
    static int N ;
    static int[] b;
    static boolean[] visited;
    static boolean[] finished;
    static boolean[] cycle;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        b = new int[N+1];
        visited = new boolean[N+1];
        finished = new boolean[N+1];
        cycle = new boolean[N+1];

        for (int i = 1; i < N+1; i++) {
            b[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = 1; i <N+1; i++) {
            if(visited[i]) continue;
            bfs(i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1 ; i <N+1 ; i++) {
            if (cycle[i]) {
                result.add(i);
            }
        }

        System.out.println(result.size());
        for(int x : result){
            System.out.println(x);
        }




    }
    static void bfs(int now) {
        visited[now] = true;
        int next = b[now];

        if (!visited[next]) {
            bfs(next);
        }
        else if (!finished[next]) {
            //사이클 발견 3->4->5
            cycle[now] = true;
            while (next != now) {
                cycle[next] = true;
                next = b[next];
            }
        }
        finished[now] = true;


    }

}
