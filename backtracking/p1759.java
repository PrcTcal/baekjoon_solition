import java.util.*;
import java.io.*;

public class p1759 {
    static char[] arr;
    static int L, C;

    public static boolean isValid(String str){
        if(str.contains("a") || str.contains("e") || str.contains("i") || str.contains("o") || str.contains("u")){
            str = str.replace("a", "").replace("e", "").replace("i", "").replace("o", "").replace("u", "");
            if(str.length() >= 2){
                return true;
            }
        }
        return false;
    }

    // 풀이 조건
    // 1. 조건을 만족하는 모든 암호가 사전식으로 정렬되야 하므로 우선적으로 char 배열을 정렬시킨다
    // 2. 깊이를 기준으로 탐색하며, 암호의 길이가 L일 때 조건을 만족하는 암호만 출력한다
    // 3. 현재 인덱스를 포함한 남은 알파벳 개수가 채워야할 알파벳 개수보다 적을 경우 가지치기를 하여 시간을 단축시킨다
    public static void dfs(int idx, String str){
        if(str.length() == L && isValid(str)){
            System.out.println(str);
            return;
        }
        if(idx >= C || (str.length() + (C - idx) < L)){
            return;
        }
        dfs(idx + 1, str + arr[idx]);
        dfs(idx + 1, str);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = br.readLine().replaceAll(" ", "").toCharArray();
        Arrays.sort(arr);
        dfs(0, "");
    }    
}
