import java.util.*;
import java.io.*;

public class p14500 {
    static int N, M;
    static int[][] paper;
    static boolean[][] visit;
    static int max;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};


    // 고려해야할 조건
    // 1. 'ㅗ' 모형을 제외한 나머지 모형들은 depth를 최대 4까지 갈 수 있는 dfs를 통해 구할 수 있다.
    // 2. 'ㅗ' 모형은 특정 좌표의 상하좌우를 하나씩만 빼가면서 값을 더해주면 구할 수 있다
    public static void dfs(int x, int y, int depth, int sum){
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }

        visit[y][x] = true;
        sum += paper[y][x];

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= M || ny >= N || visit[ny][nx]) continue;
            dfs(nx, ny, depth+1, sum);
        }   
        visit[y][x] = false;
    }

    public static void exception(int x, int y){
        int result = 0;
        for(int i = 0 ; i < 4 ; i++){
            int sum = paper[y][x];
            boolean check = true;
            for(int j = 0 ; j < 4 ; j++){
                if(i != j){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx < 0 || ny < 0 || nx >= M || ny >= N) {
                        check = false;
                        break;
                    }
                    sum += paper[ny][nx];
                }
            }
            if(check) result = Math.max(result, sum);
        }

        max = Math.max(max, result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            paper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                dfs(j, i, 0, 0);
                exception(j, i);
            }
        }

        System.out.println(max);

    }    
}
