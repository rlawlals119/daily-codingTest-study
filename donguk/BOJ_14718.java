package donguk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14718 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(sz.nextToken());
        int K = Integer.parseInt(sz.nextToken());

        ArrayList<ArrayList<Integer>> info = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer sz2 = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();
            int total = 0;
            for (int j = 0; j < 3; j++) {
                int x = Integer.parseInt(sz2.nextToken());
                total += x;
                list.add(x);
            }
            list.add(total);
            info.add(list);
        }

        info.sort((a,b)->a.get(3)-b.get(3));

        int str = 0;
        int dex = 0;
        int intell = 0;

        for (int i = 0; i < K; i++) {
            ArrayList<Integer> person = info.get(i);
            if(person.get(0)>=str) str=person.get(0);
            if(person.get(1)>=dex) dex=person.get(1);
            if(person.get(2)>=intell) intell=person.get(2);
        }
        System.out.println("str = " + str);
        System.out.println("dex = " + dex);
        System.out.println("intell = " + intell);
        int answer = str+dex+intell;
        System.out.println(answer);
    }
}
