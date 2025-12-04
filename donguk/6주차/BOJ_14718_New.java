package donguk;

import java.io.*;
import java.util.*;

// 기존 방식 : 1. 모든 조합을 다 만들어 보자 (최대 조합수 : 100C90 숫자가 기하급수적
// 두번쨰 방식 : 2. for 문 3번 돌리면 100*100*100 해서 괜찮지않아...? 틀렸음 여기에 모든 N명에 대해서 또 조사해서 사실상 100*100*100*100 이 나온다
// 세번쨰 방식 : 3. 후보군을 줄여보자
public class BOJ_14718_New {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] info = new int[N][3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){ //힘 검사
            for(int j=0;j<N;j++){ // 민첩 검사

                int str = info[i][0];
                int dex = info[j][1];

                ArrayList<Integer> list = new ArrayList<>();

                for(int k=0;k<N;k++){ // 병사 검사 인데 전부 검사하는게 아니라 먼저 힘과 민첩으로 필터링 된 애들만 검사
                    if(info[k][0] <= str && info[k][1] <= dex){
                        list.add(info[k][2]);
                    }
                }

                if(list.size() < K) continue;


                Collections.sort(list);

                int intel = list.get(K-1);

                answer = Math.min(answer, str + dex + intel);
            }
        }

        System.out.println(answer);
    }
}
