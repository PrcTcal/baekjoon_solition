import java.io.*;

public class p10451 {
    static int[][] graph;
    static boolean[] visit;

    public static void dfs(int n){
        visit[n] = true;
        for(int i = 0 ; i < visit.length ; i++){
            if(graph[n][i] == 1 && !visit[i]){
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = 0, count = 0;
        String[] tmpArr;
        for(int i = 0 ; i < T ; i++){
            N = Integer.parseInt(br.readLine());
            count = 0;
            graph = new int[N][N];
            visit = new boolean[N];
            tmpArr = br.readLine().split(" ");

            for(int j = 0 ; j < N ; j++){
                graph[j][Integer.parseInt(tmpArr[j])-1] = 1;
            }

            for(int m = 0 ; m < N ; m++){
                if(!visit[m]){
                    dfs(m);
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}