import java.util.*;
import java.io.*;

public class p10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            sb.append(map.containsKey(num) ? map.get(num) : 0).append(" ");
        }
        
        System.out.println(sb.toString().trim());
    }    
}
