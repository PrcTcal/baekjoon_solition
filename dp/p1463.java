import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
public class p1463 {

    static Integer[] dp;

    // Top-down 방식
    public static int recur(int N){
        if(dp[N] == null){
            if(N % 6 == 0){
                dp[N] = Math.min(recur(N - 1), Math.min(recur(N / 3), recur(N / 2))) + 1;
            } else if(N % 3 == 0) {
                dp[N] = Math.min(recur(N - 1), recur(N / 3)) + 1;
            } else if(N % 2 == 0) {
                dp[N] = Math.min(recur(N - 1), recur(N / 2)) + 1;
            } else {
                dp[N] = recur(N - 1) + 1;
            }
        }
        
        return dp[N];
    }

    // Bottom-up 방식
    public static int calc(int N){
        for(int i = 2 ; i <= N ; i++){
            if(i % 6 == 0){ 
                dp[i] = Math.min(dp[i-1] + 1, Math.min(dp[i/2], dp[i/3]) + 1); 
            } else if(i % 3 == 0){ 
                dp[i] = Math.min(dp[i-1] + 1, dp[i/3] + 1);
            } else if(i % 2 == 0){
                dp[i] = Math.min(dp[i-1] + 1, dp[i/2] + 1);
            } else {
                dp[i] = dp[i-1] + 1;
            }
        }
        return dp[N];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(buf.readLine());
        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;
        System.out.println(recur(N) + "\t" + calc(N));
        
    }
}

