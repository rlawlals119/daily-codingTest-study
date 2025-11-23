package donguk;

import java.util.*;

public class PG_등산코스정하기 {
    int n;
    int[][] paths;
    int[] gates;
    int[] summits;

    public int [] solution (int n , int[][] paths, int[] gates, int [] summits) {


        Set<Integer> summitsSet = new HashSet<>();
        for (int summit : summits) {
            summitsSet.add(summit);
        }

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }

        int[] distance = dijkstra(n, graph, gates, summitsSet);

        Arrays.sort(summits);

        int resultSummit = 0;
        int resultIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (distance[summit] < resultIntensity) {
                resultIntensity = distance[summit];
                resultSummit = summit;
            }
        }
        return new int[] {resultSummit,resultIntensity};
    }

    public int[] dijkstra(int n, ArrayList<ArrayList<Node>> graph, int [] gates, Set<Integer> summitsSet) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.intensity -b.intensity); // intensity 가 오름차순 형태로  정렬됨

        for (int gate : gates) {
            distance[gate] = 0;
            pq.add(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            //오래된 정보 (5,2)/ 최신 정보 (3,2)  최신정보를 사용하기 위한 코드
            if (distance[current.node] < current.intensity) continue;

            if (summitsSet.contains(current.node)) continue;

            for (Node next : graph.get(current.node)) {
                /*
                경로상 가장 큰 intensity를 찾기 위한 코드
                일반적인 다익스트라는 totalDistance = current.distance + next.distance
                 */
                int maxIntensity = Math.max(current.intensity, next.intensity);
                if (maxIntensity < distance[next.node]) {
                    distance[next.node] = maxIntensity;
                    pq.offer(new Node(next.node, maxIntensity));
                }

            }

        }



        return distance;

    }
}

class Node {
    int node;
    int intensity;

    public Node(int node, int intensity) {
        this.node = node;
        this.intensity = intensity;
    }
}
