package jeemin.week01;

public class PG_신규아이디추천 {
    public String solution(String new_id) {
        String answer = "";

        // 1단계
        new_id = new_id.toLowerCase();
        // 2단계
        new_id = new_id.replaceAll("[^a-z0-9_.-]", "");
        // 3단계
        new_id = new_id.replaceAll("\\.{2,}", ".");
        // 4단계
        if (new_id.startsWith(".")) {
            new_id = new_id.replaceFirst(".", "");
        }
        if (new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        // 5단계
        if (new_id.length() == 0) {
            new_id = "a";
        }
        // 6단계
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
        }
        if (new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        // 7단계
        if (new_id.length() <= 2) {
            String last = String.valueOf(new_id.charAt(new_id.length() - 1));
            new_id += last.repeat(3 - new_id.length());
        }
        return new_id;
    }
}
