package donguk.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    일단 i->x 가는것과 x->i 로 가는 가중치가 다 달라
    그래서 1)은  i->x 로갈때 N 번하기싫어서 x->i로 가야하는데 이때 길은 원래 그래프의 i->x 경로를 써야하니까
    그래서 reversgraph를 만든거야

    1) (i->x 최단 거리)
    2) ( x -> i 최단 거리 )

    1)
    원래 문제 시작점이 N번 바뀜으로 다익스트라를 N번 해야함 (비효율)
    해결방법 : i에서 x 로 가던 간선 방향을 x 에서 i 로 뒤집쟈
    dikstra(x,reversegraph)

    2)
    여기선 x에서 i 로 한번만 구하자
    dijkstra(x,graph)
 */
public class BOJ_1238 {
    static StringTokenizer sz;
    static int N , M , X;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sz = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(sz.nextToken());
        M = Integer.parseInt(sz.nextToken());
        X = Integer.parseInt(sz.nextToken()) -1;

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        ArrayList<ArrayList<int[]>> reverseGraph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            sz = new StringTokenizer(bf.readLine());
            int current = Integer.parseInt(sz.nextToken()) - 1;
            int next = Integer.parseInt(sz.nextToken()) - 1;
            int distance = Integer.parseInt(sz.nextToken());

            graph.get(current).add(new int[]{next, distance});
            reverseGraph.get(next).add(new int[]{current, distance});
        }

//        for (int i = 0; i < N; i++) {
//            System.out.print(i + " -> ");
//            for (int[] edge : graph.get(i)) {
//                System.out.print("(" + edge[0] + ", " + edge[1] + ") ");
//            }
//            System.out.println();
//        }

        int result = Integer.MIN_VALUE;
        int[] total = dijkstra(X, graph);
        int[] reverseTotal = dijkstra(X, reverseGraph);

        for (int i = 0; i < N; i++) {
            int tmp = total[i] + reverseTotal[i];
            if (tmp > result) {
                result = tmp;
            }
        }

        System.out.println(result);

    }
    static int[] dijkstra(int start , ArrayList<ArrayList<int[]>>graph) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        int [] visited = new int[N];
        Arrays.fill(visited, Integer.MAX_VALUE);

        queue.add(new int[]{start, 0});
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] next : graph.get(current[0])) {
                int nextNode = next[0];
                int total = next[1] + current[1];

                if (total < visited[nextNode]) {
                    visited[nextNode] = total;
                    queue.add(new int[]{nextNode, total});
                }
            }
        }
        return visited;
    }
}
