
import java.util.*;
import java.io.*;

public class p1149 {
    static int[][] dp;
    static int[][] price;

    public static int topDown(int n, int color){
        if(dp[n][color] == 0){
           if(color == 0){
               dp[n][color] = Math.min(topDown(n-1, 1), topDown(n-1, 2)) + price[n][color];
           } else if(color == 1){
               dp[n][color] = Math.min(topDown(n-1, 0), topDown(n-1, 2)) + price[n][color];
           } else if(color == 2){
               dp[n][color] = Math.min(topDown(n-1, 0), topDown(n-1, 1)) + price[n][color];
           }
        }
        return dp[n][color];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        price = new int[N][3];

        for(int i = 0 ; i < N ; i++){
            price[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        dp[0] = price[0];

        int min = Math.min(topDown(N-1, 0), Math.min(topDown(N-1, 1), topDown(N-1, 2)));
        System.out.println(min);
    }    
}
