import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class p1707 {
    static int[] visit;
    static ArrayList<int[]> graph;
/*
    public static boolean bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        visit[idx] = true;

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            
            for(int i = 0 ; i < graph.size() ; i++){
                if(graph.get(i)[0] == tmp){
                    if(!visit[graph.get(i)[1]]){
                        visit[graph.get(i)[1]] = true;
                        queue.add(graph.get(i)[1]);
                        graph.remove(i);
                    } else if(visit[graph.get(i)[1]] && queue.contains(graph.get(i)[1])) {
                        return false;
                    }
                    
                } else if(graph.get(i)[1] == tmp){
                    if(!visit[graph.get(i)[0]]){
                        visit[graph.get(i)[0]] = true;
                        queue.add(graph.get(i)[0]);
                        graph.remove(i);
                    } else if(visit[graph.get(i)[0]] && queue.contains(graph.get(i)[0])){
                        return false;
                    }   
                }
            }
        }
        return true;
    }
*/
    public static boolean dfs(int color, int idx){
        boolean check = true;
        visit[idx] = color;
        color *= -1;
        for(int i = 0 ; i < graph.size() ; i++){
            if(graph.get(i)[0] == idx || graph.get(i)[1] == idx){
                int n = graph.get(i)[0] == idx ? 1 : 0;
                int next = graph.get(i)[n];
                graph.remove(i);
                check = dfs(color, next);
                if(visit[idx] == visit[next] || !check){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] firstLine, input;
        int V, E, x, y;

        for(int i = 0 ; i < T ; i++){
            firstLine = br.readLine().split(" ");
            V = Integer.parseInt(firstLine[0]);
            E = Integer.parseInt(firstLine[1]);
            visit = new int[V];
            graph = new ArrayList<int[]>();
        
            for(int j = 0 ; j < E ; j++){
                input = br.readLine().split(" ");
                x = Integer.parseInt(input[0]) - 1;
                y = Integer.parseInt(input[1]) - 1;
                graph.add(j, new int[]{x, y});
            }

            if(dfs(1, 0)){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            
        }
    }
}
