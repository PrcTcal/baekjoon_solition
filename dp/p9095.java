import java.io.*;

public class p9095 {
    static int[] dp;

    public static int solution(int n){
        if(n < 2) return dp[n];
        if(dp[n] > 0){
            return dp[n];
        } else {
            dp[n] = solution(n-1) + solution(n-2) + (n > 2 ? solution(n-3) : 0);
            return dp[n];
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = 0;
        
        for(int i = 0 ; i < N ; i++){
            num = Integer.parseInt(br.readLine());
            dp = new int[num+1];
            dp[0] = dp[1] = 1;
            System.out.println(solution(num));
        }
    }
}
