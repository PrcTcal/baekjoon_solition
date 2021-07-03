import java.util.*;
import java.io.*;

public class p1389 {
    static int[][] graph;
    static int[] count;
    static boolean[] visit;

    public static void bfs(int N){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visit[N] = true;

        while(!queue.isEmpty()){
            int idx = queue.poll();
            for(int i = 0 ; i < graph.length ; i++){
                if(!visit[i] && graph[idx][i] == 1){
                    queue.add(i);
                    visit[i] = true;
                    count[i] = count[idx] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a, b, ans = 0, min = 101;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];

        for(int i = 0 ; i < M ; i++){
            st =new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph[a-1][b-1] = 1;
            graph[b-1][a-1] = 1;
        }

        for(int i = 0 ; i < N ; i++){
            count = new int[N];
            visit = new boolean[N];
            bfs(i);
            int tmp = Arrays.stream(count).sum();
            if(tmp < min){
                ans = i + 1;
                min = tmp;
            }
        }
        System.out.println(ans);
        
    }    
}
