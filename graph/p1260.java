import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
public class p1260 {
    static boolean[] visit;
    static int[][] graph;

    // DFS는 재귀를 이용하여 깊이로 탐색하기 때문에 
    // 가장 가까운 길로 갈 수 있는데까지 가다가 갈 곳이 없으면
    // 갈 수 있는 곳이 있는 깊이까지 되돌아온다.
    public static void dfs(int idx){
        visit[idx] = true;
        System.out.print(idx + " ");
            
        for(int i = 1 ; i < visit.length ; i++){
            if(graph[idx][i] == 1 && !visit[i]) {
                dfs(i);
            }
        }
    }

    // BFS는 너비로 탐색하고 DFS와 달리 재귀가 아닌 큐를 이용하기 때문에
    // 현재 방문한 노드에서 간선이 존재하는 노드를
    // 큐에 담아 선입선출로 체크하며 탐색하는 방식이다.
    public static void bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        visit[idx] = true;

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            System.out.print(tmp + " ");
            for(int i = 1 ; i < visit.length ; i++){
                if(graph[tmp][i] == 1 && !visit[i]){
                    visit[i] = true;
                    queue.add(i);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int V = Integer.parseInt(firstLine[2]);
        visit = new boolean[N+1];
        graph = new int[N+1][N+1];

        String[] input = new String[2];
        for(int i = 1 ; i <= M ; i++){
            input = br.readLine().split(" ");
            graph[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = 1;
            graph[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = 1;
        }

        dfs(V);
        System.out.println();
        visit = new boolean[N+1];
        bfs(V);
        System.out.println();
    }
}
