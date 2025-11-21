package donguk;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/*
뒤지게 어렵네..
   1. 처음 생각한 방식
      -  광고의 시작 시간 x 로 말하자
      -  사용자 들이 시청한 시간을 구간이라고함 (1번 구간,2번 구간..)
      - x 가 1번 구간의 시작점 보다 큰가? |-> yes : 1번 구간의 끝점 보다 작은가? |-> yes : cnt +1
                                      |                                  |
                                      |                                  |-> no : cnt 유지
                                      |
                                      |-> no : 1.(해당구간 시작점 -x) * cnt
                                               2.cnt = 0
                                               3.광고의 끝나는 시간이 3번 끝나는 시간보다 작나? |-> yes : cnt +1
                                                                                          |
                                                                                          |-> no : cnt 유지
        for문 다 돌고 남은 cnt >1 이면 한번 더 계산
    !!!시간초가뜸!!!

    2. 정답 코드 의미 해석
    1. diff : 특정 시간에 시청자가 "몇명 들어왔는지" 기록 (이 시간에 x 명 들어왔어요~ 이 시간에 x 명 나갔어요 ~)
    2. diff를 누적합 : 특정 시간에 시청자 수
    -> 의문 : 사실 2번 배열에서 광고 기간만큼 sliding window 해서 최대값 구하면 되는거 아니야 ? ex) 2번[1] + 2번[2] + 2번[3]
            : !! 시간초가 !!
    3. 시간초과 해결하기 위해 2번배열을 다시 누적합 한 배열 view -> 그러면 구간합을 다 더해서 구하는게 아니라  view2[광고 끝 시간] - view2[광고 시작 시간] 이렇게 단순 계산으로 시간 초과 줄임
       -> 시그마 개념 이라 생각하면 될듯 그 수학에서 S(3) - s(1) 이러면 2~3 까지 합 이런느낌?


 */
public class PG_광고삽입 {
    public String solution (String play_time , String adv_time , String[] logs) {
        String answer = "";
        int playSec = timeToSec(play_time);
        int advSec = timeToSec(adv_time);

        int [] diff = new int[playSec + 1];
        int[] view = new int[playSec + 1];
        int[] view2 = new int[playSec + 1];

        for (String log : logs) {
            StringTokenizer sz = new StringTokenizer(log, "-");
            int startLog = timeToSec(sz.nextToken());
            int endLog = timeToSec(sz.nextToken());
            diff[startLog] += 1;
            diff[endLog] -= 1;
        }

        view[0] = diff[0];
        for (int i = 1; i <= playSec; i++) {
            view[i] = diff[i] + view[i - 1];
        }

        view2[0] = view[0];
        for (int i = 1; i <= playSec; i++) {
            view2[i] = view[i] + view2[i - 1];
        }

        // 슬라이딩 윈도우
        int start = 0 ;
        int end = advSec-1;

        long maxSec = view2[end];
        int result = 0;

        while(end<= playSec - 1 ) {
            long tmp;
            if (start == 0) {
                tmp = view2[end];
            } else {
                tmp = view2[end] - view2[start - 1]; //이걸 모르겠음
            }

            if(tmp> maxSec) {
                maxSec = tmp;
                result = start;
            }

            start++;
            end++;
        }
        answer = SecToTime(result);
        return answer;
    }

    public int timeToSec(String time) {
        StringTokenizer sz = new StringTokenizer(time, ":");
        int totalSec = 0;
        int hour = Integer.parseInt(sz.nextToken());
        int minute = Integer.parseInt(sz.nextToken());
        int second = Integer.parseInt(sz.nextToken());

        totalSec = (hour * 60 * 60) + (minute * 60) + second;
        return totalSec;
    }

    public String SecToTime(Integer time) {
        int second = time % 60;
        int minute = (time%3600) / 60;
        int hour = time / 3600;
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }


    public static void main(String[] args) {
        PG_광고삽입 test = new PG_광고삽입();
        String [] logs ={"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        String solution = test.solution("02:03:55", "00:14:15", logs);
        System.out.println(solution);

    }
}
