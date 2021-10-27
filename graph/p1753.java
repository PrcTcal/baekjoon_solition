import java.util.*;
import java.io.*;

// 풀이 중지
public class p1753 {
    static int[][] graph;
    static int V, E, K;

    public static void dijkstra(int v){
        int[] distance = new int[V+1];
        boolean[] check = new boolean[V+1];

        for(int i = 1 ; i <= V ; i++){
            distance[i] = Integer.MAX_VALUE;
        }

        distance[v] = 0;
        check[v] = true;

        for(int i = 1 ; i <= V ; i++){
            if(!check[i] && graph[v][i] > 0){
                distance[i] = graph[v][i];
            }
        }

        for(int i = 0 ; i < V-1 ; i++){
            int min = Integer.MAX_VALUE;
            int min_idx = -1;

            for(int j = 1 ; j < V+1 ; j++){
                
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new int[V+1][V+1];

        for(int i = 1 ; i <= V ; i++){
            for(int j = 1 ; j <= V ; j++){
                graph[i][j] = -1;
            }
        }

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
        }


    }    
}
