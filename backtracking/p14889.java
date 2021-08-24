import java.util.*;
import java.io.*;

public class p14889 {
    static int N;
    static int[][] ab;
    static int[] start, link;
    static int min;

    public static int sum(){
        int ssum = 0;
        int lsum = 0;

        for(int i = 0 ; i < N ; i++){
            for(int j = i+1 ; j < N ; j++){
                if(start[i] > 0 && start[j] > 0) ssum += ab[i][j] + ab[j][i];
                if(link[i] > 0 && link[j] > 0) lsum += ab[i][j] + ab[j][i];
            }
        }

        return Math.abs(ssum - lsum);
    }

    public static void dfs(int p, int depth){
        if(depth == N/2){
            min = Math.min(min, sum());
            return;
        }
        
        for(int i = p+1 ; i < N ; i++){
            if(link[i] > 0){
                start[i] = 1;
                link[i] = 0;
                dfs(i, depth+1);
                start[i] = 0;
                link[i] = 1;
            }
        }
        
    }

    // 고려해야할 조건
    // 1. 모든 조합을 고려하되 중복이 되는 케이스를 제외하면서 수행(backtracking)
    // 2. 마지막 depth(양 팀이 동일한 인원수가 될 때)에서 각 팀의 능력치 차이를 계산해준 다음 최소값인지 비교한다
    // 3. 팀은 2개이기 때문에 0이 있는 팀과 0이 없는 팀으로 나뉜다. 즉, start 팀에는 기본적으로 0이 있다는 가정 하에 구현한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ab = new int[N][N];
        min = Integer.MAX_VALUE;
        start = new int[N];
        link = new int[N];
        
        for(int i = 0 ; i < N ; i++){
            ab[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(i > 0) link[i] = 1;
        }

        start[0] = 1;
        dfs(0, 1);
        System.out.println(min);
    }    
}
