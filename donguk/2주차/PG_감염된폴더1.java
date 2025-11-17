package donguk;

import java.util.*;

public class PG_감염된폴더1 {

    static class Result {
        boolean foundP;
        boolean foundQ;
        public Result(boolean foundP, boolean foundQ) {
            this.foundP = foundP;
            this.foundQ = foundQ;
        }
    }

    static String answer = null;
    static Map<String, ArrayList<String>> graph = new HashMap<>();
    static String P, Q;

    public static String solution(String[][] folders, String p, String q) {
        answer = null;
        graph.clear();

        P = p;
        Q = q;

        // 그래프 구성
        for (String[] f : folders) {
            String x = f[0];
            String y = f[1];

            graph.putIfAbsent(x, new ArrayList<>());
            graph.putIfAbsent(y, new ArrayList<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        dfs("root", null);

        return answer;
    }

    private static Result dfs(String curr, String parent) {
        boolean foundP = curr.equals(P);
        boolean foundQ = curr.equals(Q);

        for (String nxt : graph.getOrDefault(curr, new ArrayList<>())) {
            if (!nxt.equals(parent)) {
                Result sub = dfs(nxt, curr);
                foundP |= sub.foundP;
                foundQ |= sub.foundQ;
            }
        }

        if (foundP && foundQ && answer == null) {
            answer = curr;
        }

        return new Result(foundP, foundQ);
    }


    public static void main(String[] args) {

        String[][] folders = {
                {"root", "A"},
                {"A", "B"},
                {"A", "C"},
                {"root", "D"},
        };

        System.out.println(solution(folders, "B", "C")); // A
        System.out.println(solution(folders, "B", "D")); // root
    }
}
