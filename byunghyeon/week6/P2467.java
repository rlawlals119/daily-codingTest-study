package byunghyeon.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer[] arr = new Integer[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 모두 양수인 경우
        if(arr[0] >= 0 && arr[arr.length-1] >= 0){
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }
        // 2. 모두 음수인 경우
        if(arr[0] <= 0 && arr[arr.length-1] <= 0){
            System.out.println(arr[arr.length-2] + " " + arr[arr.length-1]);
            return;
        }

        // 3. 음수 양수 섞인 경우
        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];
        int start = 0, end = arr.length-1;
        while(start < end){
            int n1 = arr[start];
            int n2 = arr[end];

            int diff = Math.abs(n1+n2);
            if(diff < min){
                //System.out.println(n1 + " " + n2 + " " + min);
                min = diff;
                ans = new int[]{n1, n2};
            }

            if(Math.abs(arr[start+1] + arr[end]) > Math.abs(arr[start] + arr[end-1])){
                end--;
            }else{
                start++;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
