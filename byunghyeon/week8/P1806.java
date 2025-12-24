package byunghyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] seqs = new int[N+1];
        for(int i = 1; i <= N; i++){
            seqs[i] += seqs[i-1] + Integer.parseInt(st.nextToken());
        }

        for (int seq : seqs) {
            System.out.print(seq + " ");
        }
        System.out.println();

        int min = Integer.MAX_VALUE;
        int left = 0, right = N;
        while(right <= N && left < right){

            if(seqs[right]-seqs[left] >= S){
                right--;
                min = Math.min(right-left+1, min);
            }else{
                left++;
                while(right <= N && seqs[right]-seqs[left] < S){
                    right++;
                }
            }

        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
