package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5972 {
    static int N, M;
    static Map<Integer, Node> graph = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(a, new Node(a));
            graph.putIfAbsent(b, new Node(b));

            graph.get(a).addEdge(b, c);
            graph.get(b).addEdge(a, c);
        }

        int[] dist = dijkstra();
        System.out.println(dist[N-1]);

    }

    public static int[] dijkstra(){
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int []> pq = new PriorityQueue<>(
                (a,b) -> a[1] - b[1]
        );
        pq.add(new int[]{0, 0}); // num, w

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int n = cur[0];
            int d = cur[1];

            for (Edge edge : graph.get(n).edges) {
                int next = edge.num;
                int nextDist = edge.w + d;

                if(nextDist < dist[next]){
                    dist[next] = nextDist;
                    pq.add(new int[]{next, nextDist});
                }
            }
        }

        return dist;
    }

    static class Node{
        int num;
        List<Edge> edges = new ArrayList<>();
        public Node(int num){
            this.num = num;
        }
        public void addEdge(int n, int w){
            for (Edge edge : edges) {
                if(edge.num == n){
                    if(w < edge.w){
                        edge.w = w;
                    }
                    return;
                }
            }

            edges.add(new Edge(n, w));
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
