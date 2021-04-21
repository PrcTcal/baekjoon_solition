import java.io.*;

public class p11726 {

    static int[] dp;

    public static int topDown(int n){
        if(n == 1 || n == 0) return 1;
        if(dp[n] > 0) {
            return dp[n];
        } else {
            dp[n] = topDown(n-1) + topDown(n-2);
            dp[n] %= 10007;
            return dp[n];
        }
        
    }

    
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(buf.readLine());
        dp = new int[N+1];
        dp[0] = dp[1] = 1;
        System.out.println(topDown(N));
    }
}
