package jeemin.week01;
import java.util.*;

public class LC_785 {
    public boolean isBipartite(int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0 && graph[i].length != 0) {
                q.offer(i);
            }
            while (!q.isEmpty()) {
                int curr_n = q.poll();

                for (int next : graph[curr_n]) {
                    if (visited[next] == 0) {
                        q.offer(next);
                        visited[next] = 3 - visited[curr_n];
                    }
                    else if (visited[curr_n] == visited[next]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
