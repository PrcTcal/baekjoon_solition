
import java.io.*;
import java.util.*;

public class p1010 {
    static int[][] dp = new int[30][30];
    // site[0] : west 개수
    // site[1] : east 개수
    public static int combination(int west, int east){
        if(dp[west][east] > 0) return dp[west][east];
        if(east == west || west == 0){
            return dp[west][east] = 1;
        } else {
            return dp[west][east] = combination(west - 1, east - 1) + combination(west, east - 1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] site;
        for(int i = 0 ; i < N ; i++){
            site = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(combination(site[0], site[1]));
        }
    }
}
