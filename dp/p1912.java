import java.util.*;
import java.io.*;

public class p1912 {
    
    // 고려해야할 조건
    // 1. dp는 각 idx까지의 연속된 합의 최대값을 나타낸다.
    // 2. 현재 idx까지의 합보다 현재 idx의 값이 더 크다면 이전까지의 연속된 값의 합은 의미가 없다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp[0] = input[0];
        int max = dp[0];
        
        for(int i = 1 ; i < input.length ; i++){
            dp[i] = Math.max(dp[i-1] + input[i], input[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }    
}
