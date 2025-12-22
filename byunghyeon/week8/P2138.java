package byunghyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2138 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        String target = br.readLine();

        // ROUND 1
        char[] base = input.toCharArray();
        int result = tryTransformation(base, target, 0);
        if(result != -1){
            System.out.println(result);
            return;
        }

        // Round 2
        base = input.toCharArray();
        base[0] = base[0] == '1' ? '0' : '1';
        base[1] = base[1] == '1' ? '0' : '1';
        result = tryTransformation(base, target, 1);

        System.out.println(result);
    }

    public static int tryTransformation(char[] base, String target, int count){

        for(int i = 1; i < N - 1; i++){
            if(base[i-1] != target.charAt(i-1)){
                base[i-1] = base[i-1] == '1' ? '0' : '1';
                base[i] = base[i] == '1' ? '0' : '1';
                base[i+1] = base[i+1] == '1' ? '0' : '1';
                count++;
            }
        }

        if(base[N-1] != target.charAt(N-1)
                && base[N-2] != target.charAt(N-2)){
            base[N-1] = base[N-1] == '1' ? '0' : '1';
            base[N-2] = base[N-2] == '1' ? '0' : '1';
            count++;
        }

        String finish = String.valueOf(base);
        return finish.equals(target) ? count : -1;
    }
}
