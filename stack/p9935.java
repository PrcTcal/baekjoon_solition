import java.util.*;
import java.io.*;

public class p9935 {

    // 시간 초과나는 방법
    // indexOf는 시작 인덱스를 명시하지 않을 경우 0에서부터 탐색을 한다
    // 즉, 아래의 코드는 매번 0에서부터 sb를 순회하면서 t에 해당하는 단어가 있는지 검색하기 때문에 O(n^2)가 되어 시간이 초과된다
    // 이를 방지하기 위해선 sb에 한글자씩 쌓으면서 t가 있는지 없는지를 판별하는 방식으로 구현해야 한다.
    // public static String solution(String s, String t){
    //     StringBuilder sb = new StringBuilder(s);
    //     int idx;
    //     while((idx = sb.indexOf(t)) >= 0){
    //         sb.delete(idx, idx + t.length());
    //     }
    //     return sb.length() > 0 ? sb.toString() : "FRULA";
    // }

    public static String solution(String s, String t){
        StringBuilder sb = new StringBuilder();
        int bombLen = t.length();

        for(int i = 0 ; i < s.length() ; i++){
            sb.append(s.charAt(i));
            if(sb.length() >= bombLen){
                boolean isSame = true;
                for(int idx = 0 ; idx < bombLen ; idx++){
                    char c1 = sb.charAt(sb.length() - bombLen + idx);
                    char c2 = t.charAt(idx);
                    if(c1 != c2){
                        isSame = false;
                        break;
                    }
                }
                if(isSame){
                    sb.delete(sb.length() - bombLen, sb.length());
                }
            }
        }

        return sb.length() > 0 ? sb.toString() : "FRULA";
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String exp = br.readLine();
        System.out.println(solution(str, exp));
    }    
}
