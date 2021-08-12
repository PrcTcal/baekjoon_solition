import java.util.*;
import java.io.*;

public class p12865 {
    static int N, K, V;
    static Integer[][] dp;      // 물건 개수, 무게
    static int[][] input;

    // i : 물건 번호, k : 총 무게
    public static int topDown(int i, int k){
        if(i < 0) return 0;
        if(dp[i][k] == null){
            if(input[i][0] > k) {
                dp[i][k] = topDown(i-1, k);
            } else {
                dp[i][k] = Math.max(topDown(i-1, k), topDown(i-1, k - input[i][0]) + input[i][1]);
            }
        }
        return dp[i][k];
    }

    // 고려해야할 조건
    // 1. dp 배열은 0 부터 K까지의 무게에 대해서 물건 번호(i)마다 탐색했을 때 들어갈 수 있는 최대의 가치를 나타낸다
    // 2. 만약 i번 물건을 넣을 때 들어갈 수 있는 무게 K보다 현재 물건의 무게가 크다면 넣을 수 없으므로 가치의 최대값은 이전 물건에서의 dp값과 같다
    // 3. 만약 i번 물건을 넣을 수 있다면 이전 물건에서의 dp값과 i번 물건의 가치 + i번 물건의 무게를 뺀 무게에서의 topDown값을 비교해야한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new Integer[N+1][K+1];
        input = new int[N+1][2];

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(topDown(N, K));
    }    
}
