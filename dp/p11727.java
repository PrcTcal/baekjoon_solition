import java.io.*;
public class p11727 {

    static int[] dp;

    public static int solution(int N){
        if(N < 2) return dp[N];
        if(dp[N] > 0){
            return dp[N];
        } else {
            dp[N] = solution(N-1) + 2 * solution(N-2);
            dp[N] %= 10007;
            return dp[N];
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[0] = dp[1] = 1;
        System.out.println(solution(N));
    }
}
