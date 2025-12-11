package jeemin.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            int input = Integer.parseInt(br.readLine());
            nums[i] = input;
        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 1; i < N + 1; i++) {
            boolean[] visited = new boolean[N + 1];
            Queue<Integer> q = new LinkedList<>();
            q.add(nums[i]);
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == i) {
                    set.add(i);
                    break;
                }
                if (visited[nums[cur]]) {
                    break;
                }
                q.add(nums[cur]);
                visited[nums[cur]] = true;
            }
        }
        System.out.println(set.size());
        for (int s : set) {
            System.out.println(s);
        }

    }
}
