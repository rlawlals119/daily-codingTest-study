package donguk.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
1.가장 많은 별을 포함 시키는 방법이 뭘까 ?
-> 별을 최대한 가장자리에 위치 시키고 트렘펄린의 나머지 공간이 다른 별을 포함 시키면 된다

2.그러면 모든 탐색을 다하기에는 n과m의 범위가 너무 커서 별을 기준으로 탐색을 시도하면 어떨까 ?
-> 가장 먼저 든 생각이 한개의 별을 꼭지점으로 트렘펄린의 한개의 꼭지점으로 고정시키고 4방 탐색을 하면 되자 않을까 (좌상단,좌하단,우상단,우하단)
   이렇게 되면 답 중에 별이 꼭지점이아닌 중간 지점에 껴 있을때가 답일 수가 있는데 즉 모든 경우의수를 탐색을 못해

3. 그러면 하나의 별을 잡고 그 별의 x 좌표가 사각형 변의 첫번쨰 두번쨰 세번째 ... y좌표가 사각형 의 첫번쨰 두번쨰 세번째 이렇게 하면 어때?
-> 모든 경우의 수를 다 탐색할려고 노력했지만 이경우 모든 n과m을 다 탐색해야해서 의미가 없어

4. 결국 답지를 찾아봤는데..
-> 2개의 별 (별1,별2)를 뽑아서 별1 의 x 좌표 별2의 y좌표를 가져와 만들어진 (별1의x,별2의y) 를 좌상단으로 고정해서 하자

5.그럼 왜 하필 별 2개야 ? 별 3개 4개 5개 .. 이러면 안돼?? 어차피 최대한 많은 별을 가장자리에 포함 해야 한다며..
-> 별의 수가 많아 진다 하더라도 특정지어지는 좌표는 1개가 나온다 결국 별 2개를 뽑나 3개를 뽑나 결과는 우리가 필요할 고정할 좌표 1개가 나옴으로
   별 2개를 뽑는게 효율적이다 만약 z 축도 있었으면 3개를 뽑아서 x,y,z 를 결정하면 될듯!

6. 또 함정인게 이게 나만 그렇게 생각 할 수 있는데 BFS 에서 메트릭스 쓰는것처럼 board 밖으로 나가면 끝! 이 아니라
   여기선 별똥별이 떨어지는 위치 밖으로 트렘펄린 이 나가도 상관 x

 */
public class BOJ_1468 {
    static StringTokenizer sz;
    static int N,M,L, K;
    static ArrayList<int[]> stars;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sz = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(sz.nextToken());
        M = Integer.parseInt(sz.nextToken());
        L = Integer.parseInt(sz.nextToken());
        K = Integer.parseInt(sz.nextToken());
        int cnt = 0;

        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            sz = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(sz.nextToken());
            int y = Integer.parseInt(sz.nextToken());
            stars.add(new int[]{x, y});
        }

        int maxCnt = Integer.MIN_VALUE;
        for (int[] star1 : stars) {
            for (int[] star2 : stars) {
                int pinX = star1[0];
                int pinY = star2[1];

                cnt = check(pinX, pinY);
                if(cnt>maxCnt) maxCnt = cnt;
            }
        }
        System.out.println(K-maxCnt);
    }

    static int check(int pinX, int pinY ) {
        int cnt = 0;
        for (int[] candidate : stars) {
            int candiX = candidate[0];
            int candiY = candidate[1];

            if (candiX >= pinX && candiX <= pinX + L && candiY >= pinY && candiY <= pinY + L) {
                cnt++;
            }
        }
        return cnt;
    }
}
