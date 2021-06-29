import java.util.*;
import java.io.*;

public class p1012 {
    static int[][] graph;
    static boolean[][] visit;
    static int count;
    static int M, N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while(!queue.isEmpty()){
            x = queue.peek()[0];
            y = queue.peek()[1];
            visit[x][y] = true;
            queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int gx = x + dx[i];
                int gy = y + dy[i];
                if(gx >= 0 && gx < M && gy >= 0 && gy < N){
                    if(!visit[gx][gy] && graph[gx][gy] == 1){
                        queue.add(new int[]{gx, gy});
                        visit[gx][gy] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int X, Y, K;

        for(int i = 0 ; i < T ; i++){
            String[] field = br.readLine().split(" ");
            M = Integer.parseInt(field[0]);
            N = Integer.parseInt(field[1]);
            K = Integer.parseInt(field[2]);
            graph = new int[M][N];
            visit = new boolean[M][N];
            count = 0;
            for(int j = 0 ; j < K ; j++){
                String[] cabbage = br.readLine().split(" ");
                X = Integer.parseInt(cabbage[0]);
                Y = Integer.parseInt(cabbage[1]);
                graph[X][Y] = 1;
            }
             
            for(int a = 0 ; a < M ; a++){
                for(int b = 0 ; b < N ; b++){
                    if(graph[a][b] == 1 && !visit[a][b]){
                        bfs(a, b);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }    
}
