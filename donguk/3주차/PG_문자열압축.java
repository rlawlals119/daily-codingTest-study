package donguk;
public class PG_문자열압축 {
    public static int  solution(String s) {
        int n = s.length();
        if (n ==1) return 1;
        int answer = n;

        for (int i = 1 ; i <= n/2 ; i++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, i);
            int count = 1;
            for (int j = i; j < n; j += i) {
                int end = Math.min(n, j + i);
                String curr = s.substring(j, end);

                if (curr.equals(prev)) {
                    count++;
                } else {
                    if (count > 1) {
                        sb.append(count);
                    }

                    sb.append(prev);
                    prev = curr;
                    count = 1;
                }
            }

            if (count > 1) {
                sb.append(count);
            }
            sb.append(prev);

            answer = Math.min(answer, sb.length());

        }
        return answer;
    }
    //메인
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }
}