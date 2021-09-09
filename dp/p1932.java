import java.util.*;
import java.io.*;

public class p1932 {
    // static int[] dp;     // dp라기보단 그냥 풀이에 가까운 방법
    static Integer[][] dp;      // memoization용 Integer 배열(숫자 범위에 0도 포함이여서 아직 구하지 못한 부분은 null로 표현하기 위해 Integer 객체 배열 사용)
    static int[][] arr;         // 입력으로 받은 삼각형 내 숫자들
    static int N;

    public static int topDown(int depth, int idx){
        // dp에 저장된 마지막 라인의 값
        if(depth == N - 1) return dp[depth][idx];

        // dp에 아직 저장되지 않은 값들은 양쪽 아래의 최대값과 현재 depth, idx의 삼각형 값을 더하여 구한다.
        if(dp[depth][idx] == null){
            dp[depth][idx] = arr[depth][idx] + Math.max(topDown(depth+1, idx), topDown(depth+1, idx+1));
        }
        return dp[depth][idx];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new Integer[N][N];

        for(int i = 0 ; i < N ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < i+1 ; j++){
                arr[i][j] = input[j];
            }
        }

        // 제일 끝 라인에서부터 root로 올라가며 최대값을 저장할 것이므로 dp의 마지막 라인에 삼각형 마지막 라인 숫자들을 채워넣음
        for(int i = 0 ; i < N ; i++){
            dp[N-1][i] = arr[N-1][i];
        }

        System.out.println(topDown(0, 0));
        
        // dp라기보단 그냥 수학적 풀이에 가까움
        // dp = new int[N];
        // dp[0] = Integer.parseInt(br.readLine());

        // for(int i = 1 ; i < N ; i++){
        //     int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //     int tnum = 0;
        //     for(int j = 0 ; j < i ; j++){
        //         int tmp = dp[j];
        //         dp[j] = Math.max(tnum, tmp + num[j]);
        //         tnum = tmp + num[j+1];
        //     }
        //     dp[i] = tnum;
        // }
        // int max = 0;
        // for(int i = 0 ; i < N ; i++){
        //     max = Math.max(max, dp[i]);
        // }
        // System.out.println(max);
    }      
}
