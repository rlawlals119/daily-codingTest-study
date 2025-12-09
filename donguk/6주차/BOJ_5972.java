package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int M = Integer.parseInt(sz.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer sz2 = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(sz2.nextToken());
            int B = Integer.parseInt(sz2.nextToken());
            int C = Integer.parseInt(sz2.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        System.out.println(dijkstra(N, graph));

    }

    static int dijkstra(int N, ArrayList<ArrayList<Node>> graph) {
        int[] cost = new int[N + 1];
        Arrays.fill(cost,Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cow - b.cow);

        cost[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (cost[current.node] < current.cow) continue;

            for (Node next : graph.get(current.node)) {
                int totalCost = current.cow + next.cow;
                if (totalCost < cost[next.node]) {
                    cost[next.node] = totalCost;
                    pq.offer(new Node(next.node, totalCost));
                }
            }
        }
        return cost[N];
    }

    static class Node{
        int node;
        int cow;

        public Node(int node, int cow) {
            this.node = node;
            this.cow = cow;
        }
    }
}
