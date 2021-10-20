import java.util.*;
import java.io.*;

public class p14501 {
    static int N;
    static int[] p, t, dp;

    // 고려해야할 조건
    // 1. dp는 해당 날짜부터 마지막날까지 얻을 수 있는 최대 수익을 의미한다
    // 2. top-down 방식으로 구현하였다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        p = new int[N+1];
        t = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 마지막 날의 최대 수익은 마지막 날의 상담이 걸리는 일수가 1일때에만 수익을 담을 수 있기 때문에 t[N]에 따라 p[N]이거나 0이다.
        dp[N] = t[N] + N <= N+1 ? p[N] : 0;

        // top-down 방식이므로 뒤에서부터 인덱스가 내려간다.
        for(int i = N-1 ; i > 0 ; i--){
            // next는 오늘 상담을 맡았을 때 오늘 맡은 상담이 끝나고 새로운 상담을 받을 수 있는 첫날을 의미한다.
            int next = i + t[i];

            // 오늘 예정된 상담이 기한인 N일 내에 끝낼 수 있을 경우
            if(next <= N+1){
                // 오늘 상담을 맡는 경우와 맡지 않는 경우 중 최대 값을 memoization한다.
                // 상담을 맡는 경우 p[i]와 오늘 맡은 상담이 끝난 다음날의 최대 수익( dp[i + t[i]] )의 합이 최대 수익이 된다.
                // 상담을 맡지 않는 경우 내일의 최대 수익(dp[i+1])이 오늘의 최대 수익이 된다.
                dp[i] = Math.max(p[i] + (next < N+1 ? dp[next] : 0), dp[i+1]);
                
            // 오늘 예정된 상담이 기한 내에 끝낼 수 없어 받을 수 없는 경우
            } else {
                dp[i] = dp[i+1];
            }
        }

        System.out.println(dp[1]);

    }    
}
