package donguk.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String letters = bf.readLine();
        String explosive = bf.readLine();
        Stack<Character> stack = new Stack<>();

//        System.out.println(check);
        for (int i = 0; i < letters.length(); i++) {
            stack.push(letters.charAt(i));

            if(stack.size() >= explosive.length() && stack.peek() == explosive.charAt(explosive.length()-1)) {

                boolean flag = true;
                for (int j = 0; j < explosive.length(); j++) {
                    if(stack.get(stack.size() - explosive.length() +j) != explosive.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < explosive.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }


        if(stack.isEmpty()) {
            System.out.println("FRULA");
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(Character c : stack) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }

    }
}
