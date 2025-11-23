package donguk;

import java.util.ArrayList;
import java.util.Arrays;

public class Pratice {
    static int N = 4, R = 3, C = 0;
    int[] a = {1, 2, 3, 4};
    int[] b = new int[R];
    boolean[] visited = new boolean[N];

    public void perm(int cnt) {

        if (cnt == R) {
            System.out.println(Arrays.toString(b));
            C++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            b[cnt] = a[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }

    public void comb(int cnt, int start) {
        if (cnt == R) {
            System.out.println(Arrays.toString(b));
            C++;
            return;
        }

        for (int i = start; i < N; i++) {
            b[cnt] = a[i];
            comb(cnt + 1, i + 1);
        }
    }

    public void subs(int cnt, String str) {
        if (cnt == N) {
            System.out.println(str);
            C++;
            return;
        }

        subs(cnt + 1, str);
        subs(cnt + 1, str + a[cnt]);
    }

    public void subsList(int cnt, ArrayList<Integer> list) {

        if (cnt == N) {
            System.out.println(list);
            C++;
            return;
        }

        subsList(cnt + 1, list);
        list.add(a[cnt]);
        subsList(cnt + 1, list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Pratice p = new Pratice();

        C = 0;
        p.perm(0);
        System.out.println("perm C = " + C);
        System.out.println("----------------------------");

        C = 0;
        p.comb(0, 0);
        System.out.println("comb C = " + C);
        System.out.println("----------------------------");

        C = 0;
        p.subs(0, "");
        System.out.println("subs C = " + C);
        System.out.println("----------------------------");

        C = 0;
        p.subsList(0, new ArrayList<>());
        System.out.println("subsList C = " + C);
        System.out.println("----------------------------");
    }
}
