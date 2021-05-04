import java.io.*;
public class p10844 {
    static Long[][] dp;
    final static long MOD = 1000000000;

    public static long topDown(int n, int num){
        if(n == 1) return dp[n][num];
        if(dp[n][num] == null){
            if(num == 0){
                dp[n][num] = topDown(n-1, 1);
            } else if(num == 9){
                dp[n][num] = topDown(n-1, 8);
            } else {
                dp[n][num] = topDown(n-1, num-1) + topDown(n-1, num+1);
            }
        }
        return dp[n][num] % MOD;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 0;
        dp = new Long[N+1][10];

        for(int i = 0 ; i < 10 ; i++){
            dp[1][i] = 1L;
        }
        
        for(int i = 1 ; i < 10 ; i++){
            result += topDown(N, i);
        }

        System.out.println(result % MOD);
    }
}

// n = 1 : 1 ~ 9 => 9
// n = 2 : 12 10 / 21 23 / 32 34 / 43 45 / 54 56 / 65 67 / 76 78 / 87 89 / 98 => 17
// n = 3 : 123 121 101 / 234 210 212 232 / 345 321 323 343 / 456 432 434 454 / 567 543 545 565 /
//         678 654 656 676 / 789 765 767 787 / 876 878 898 / 987 989 => 32
// n = 4 : 1234 1232 1212 1010 1012 1210 / 2345 2343 2323 2321 2101 2123 2121 / 3456 3454 3434 3432 3210 3212 3232 3234
//         4567 4565 4545 4543 4321 4323 4343 4345 / ... / 6789 6787 6767 6765 6543 6545 6565 6567 / 7898 7878 7876 7654 7656 7676 7678
//         8989 8987 8765 8767 8787 8789 / 9876 9878 9898 => 6, 7, 8, 8, 8, 8, 7, 6, 3 = 61
// dp[1] = 9 
// dp[2] = dp[1] * 2 - 1 = 18 - 1 = 17
// dp[3] = dp[2] * 2 - 2 = 34 - 2 = 32
// dp[4] = dp[3] * 2 - 3 = 64 - 3 = 61
// ...
// dp[n] = dp[n-1] * 2 - (n-1)
// 처음에 점화식으로 접근하였으나 접화식을 구하는 방식이 잘못됬던 것 같다.
// 숫자 0와 9 옆에는 각각 1과 8만이 붙을 수 있다는 점을 가지고 다시 접근했다.
// 자리를 나타내는 n과 자리 n에 위치한 숫자 num을 가지고 2차원 배열을 만들어서
// top-down 방식을 통해 해결할 수 있었다.