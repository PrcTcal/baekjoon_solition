import java.util.*;
import java.io.*;

// 미해결 문제
// 원인 : 제출시 StackOverFlow 예외가 발생했는데, 최악의 상황인 N = 100이고
//        그래프의 모든 입력이 1일 경우 topDown()이 너무 많이 호출되서 그런 것으로 예상됨.
public class p1890 {
    static int N;
    static int[][] graph;
    static long[][] dp;
    static int[] dx = {-1, 0};
    static int[] dy = {0, -1};

    public static long topDown(int x, int y){
        if(x == N-1 && y == N-1) return 1;
        if(dp[y][x] == 0){
            if(x + graph[y][x] < N) dp[y][x] = topDown(x + graph[y][x], y);
            if(y + graph[y][x] < N) dp[y][x] += topDown(x, y + graph[y][x]);
        }
        return dp[y][x];
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new long[N][N];

        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(topDown(0, 0));
    }    
}
