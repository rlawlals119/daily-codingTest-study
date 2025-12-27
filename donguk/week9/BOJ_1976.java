package donguk.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1976 {
    static StringTokenizer sz;
    static ArrayList<ArrayList<Integer>> graph;
    static int N;
    static int M;
    static ArrayList<Integer> travel;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            sz = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(sz.nextToken());
                if (x == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        sz = new StringTokenizer(bf.readLine());
        travel = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(sz.nextToken())-1;
            travel.add(x);
        }

        bfs(travel.get(0));

    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int curr = queue.poll();

            for(int next : graph.get(curr)) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        for (int city : travel) {
            if(!visited[city]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
