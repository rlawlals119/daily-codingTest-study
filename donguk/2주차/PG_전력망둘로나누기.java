package donguk;

import java.util.ArrayList;

public class PG_전력망둘로나누기 {
    static ArrayList<ArrayList<Integer>> graph;
    public int solution(int n , int[][] wires) {
        int answer = -1;
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            int x = wire[0] -1;
            int y = wire[1] -1;
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int[] subtree = new int[n];
        dfs(0, -1, subtree);

        int minValue = Integer.MAX_VALUE;
        for (int tmp : subtree) {
            int diff = Math.abs(n - 2 * tmp);
            minValue = Math.min(minValue, diff);
        }

        return minValue;
    }

    public int dfs(int curr, int parent,int [] subtree) {
        int answer = 1;

        for (int next : graph.get(curr)) {
            if (next == parent) continue;
            answer += dfs(next, curr,subtree);
        }
        subtree[curr] = answer;
        return answer;
    }

    public static void main(String[] args) {
        PG_전력망둘로나누기 sol = new PG_전력망둘로나누기();
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int result = sol.solution(9, wires);
        System.out.println("result = " + result);

    }
}
