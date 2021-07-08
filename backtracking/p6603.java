import java.util.*;
import java.io.*;

public class p6603 {
    static int[] arr;
    static int K;

    public static void dfs(int idx, int depth, String str){
        if(depth == 6){
            System.out.println(str);
            return;
        }
        
        if(idx >= K || (K - idx < 6 - depth)) return;

        dfs(idx + 1, depth + 1, str.length() > 0 ? str + " " + arr[idx] : "" + arr[idx]);
        dfs(idx + 1, depth, str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        while(input.length > 1){
            K = Integer.parseInt(input[0]);
            arr = new int[K];
            
            for(int i = 1 ; i < input.length ; i++){
                arr[i-1] = Integer.parseInt(input[i]);
            }
            
            dfs(0, 0, "");
            System.out.println();

            input = br.readLine().split(" ");
        }
    }
}
