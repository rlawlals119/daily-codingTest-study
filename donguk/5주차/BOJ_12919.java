package donguk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12919 {
    static boolean result = false;
    static String start ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        start = br.readLine();
        String target = br.readLine();

        dfs(new StringBuilder(target));
        System.out.println(result?1:0);

    }
    static void dfs(StringBuilder curr) {
//        System.out.println("curr = " + curr);
        if(result) return;

        if(curr.length() < start.length()) return;

        if (curr.toString().equals(start)) {
            result = true;
            return;
        }

        if (curr.charAt(curr.length() - 1) == 'A') {
            curr.deleteCharAt(curr.length() - 1);
            dfs(curr);
            curr.append('A');
        }

        if(curr.charAt(0) == 'B') {
            curr.reverse();
            curr.deleteCharAt(curr.length() - 1);
            dfs(curr);
            curr.append('B').reverse();
        }

        //
    }
}
