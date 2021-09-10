import java.util.*;
import java.util.regex.*;
import java.io.*;

public class IdRecommend {

    public static String solution(String new_id){
        String answer = new_id.toLowerCase();
        answer = answer.replaceAll("[~!@#$%^&*()=+\\[\\{\\]\\}\\:?,<>/]", "");
        answer = answer.replaceAll("\\.{2,}", ".");
        answer = answer.replaceAll("^\\.|\\.$", "");
        if(answer.length() == 0) {
            answer = "a";
        } else if(answer.length() >= 16){
            answer = answer.substring(0, 15).replaceAll("\\.$", "");
        }
        if(answer.length() <= 2){
            StringBuilder sb = new StringBuilder(answer);
            while(sb.length() <= 2){
                sb.append(answer.charAt(answer.length()-1));
            }
            answer = sb.toString();
        }
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
    }    
}
