package byunghyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P4485 {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int cases = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            int[][] cave = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 그래프 생성
            Map<Integer, Node> graph = new HashMap<>();
            for(int i = 0; i < N*N; i++){
                graph.putIfAbsent(i, new Node(i));
                if(i-N >= 0){ // 위
                    graph.get(i).addEdge(i-N, cave[(i-N)/N][(i-N)%N]);
                }
                if(i+N < N*N){ // 아래
                    graph.get(i).addEdge(i+N, cave[(i+N)/N][(i+N)%N]);
                }
                if(i+1 < N*N && (i+1)/N == i/N){ // 오른쪽
                    graph.get(i).addEdge(i+1, cave[(i+1)/N][(i+1)%N]);
                }
                if(i-1 >= 0 && (i-1)/N == i/N){ // 왼쪽
                    graph.get(i).addEdge(i-1, cave[(i-1)/N][(i-1)%N]);
                }
            }

            // 다익스트라
            int[] costs = dijkstra(N, graph);
            answer.append("Problem ").append(cases).append(": ").append(cave[0][0]+costs[N*N-1]).append("\n");
            cases++;
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(int N, Map<Integer, Node> graph){
        int[] table = new int[N*N];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (a,b) -> a.w - b.w
        );
        pq.offer(new Edge(0, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(cur.w > table[cur.num]) continue;
            Node node = graph.get(cur.num); // 현재

            List<Edge> edges = node.edges; // 다음
            for (Edge edge : edges) {
                int next = edge.num;
                int nextCost = edge.w + cur.w;

                if(nextCost < table[next]){
                    table[next] = nextCost;
                    pq.add(new Edge(next, nextCost));
                }
            }
        }

        return table;
    }

    static class Node{
        int num;
        List<Edge> edges = new ArrayList<>();
        public Node(int num){
            this.num = num;
        }
        public void addEdge(int num, int w){
            edges.add(new Edge(num, w));
        }
    }

    static class Edge{
        int num;
        int w;
        public Edge(int num, int w){
            this.num = num;
            this.w = w;
        }
    }
}
