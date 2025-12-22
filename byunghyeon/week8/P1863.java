package byunghyeon.week8;
// 30
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(pq.isEmpty()) {
                if(y == 0) continue;
                pq.add(y);
                continue;
            }

            if(y == 0){
                while(!pq.isEmpty()){
                    pq.poll();
                    count++;
                }
                continue;
            }

            if(pq.peek() < y && !pq.contains(y)){
                pq.offer(y);
            }else{
                while(!pq.isEmpty() && pq.peek() > y){
                    pq.poll();
                    count++;
                }
                if(!pq.contains(y)){
                    pq.offer(y);
                }
            }
        }

        while(!pq.isEmpty()){
            pq.poll();
            count++;
        }

        System.out.println(count);

    }
}
