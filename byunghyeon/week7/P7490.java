package byunghyeon.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P7490 {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            StringBuilder ex = new StringBuilder();
            ex.append(1);
            makingZero(ex, N, 2);
            answer.append("\n");
        }

        answer.setLength(answer.length()-1);
        System.out.print(answer);
    }

    static public void makingZero(StringBuilder ex, int N, int start){

        // 문자열 만들기 끝
        if(start > N){
            String refinedEx = ex.toString().replace(" ", "");
            String[] parts = refinedEx.split("(?=[+-])");
            int sum = 0;
            for(int i = 0; i < parts.length; i++){
                if(parts[i].charAt(0) == '+'){
                    sum+=Integer.parseInt(parts[i].substring(1));
                }else{
                    sum+=Integer.parseInt(parts[i]);
                }
            }

            if(sum == 0){
                answer.append(ex).append("\n");
            }

            return;
        }

        // '+' ,'-' ,'공백' 삽입
        ex.append(" ").append(start);
        makingZero(ex, N, start+1);
        ex.setLength(ex.length()-2);

        ex.append("+").append(start);
        makingZero(ex, N, start+1);
        ex.setLength(ex.length()-2);

        ex.append("-").append(start);
        makingZero(ex, N,start+1);
        ex.setLength(ex.length()-2);
    }
}
