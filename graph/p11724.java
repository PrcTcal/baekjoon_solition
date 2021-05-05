import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class p11724 {
    static boolean[] visit;
    static int[][] graph;
    static ArrayList<Integer> left = new ArrayList<>();

    // 연결 요소의 개수를 구하는 문제로, 간선으로 연결된 그래프의 총 개수를 구해야 한다.
    // 연결되어있지 않으면 탐색되지 않아서 visit 배열의 모든 값이 true가 되지 않기 때문에
    // 값이 false로 남아있는 visit 배열의 인덱스(정점 번호)를 값으로 갖는 ArrayList를 하나 만들어서
    // 해당 ArrayList가 비지 않으면 남아있는 첫번째 정점부터 탐색하고 BFS를 시행한 숫자를 카운트하여 풀었다.
    public static void bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx-1);
        visit[idx-1] = true;

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            for(int i = 0 ; i < visit.length ; i++){
                if(graph[tmp][i] == 1 && !visit[i]){
                    visit[i] = true;
                    left.remove(left.indexOf(i+1));
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
        int count = 0;
        visit = new boolean[N];
        graph = new int[N][N];

        for(int i = 0 ; i < M ; i++){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
        
        for(int i = 1 ; i <= N ; i++){
            left.add(i);
        }

        while(!left.isEmpty()){
            bfs(left.remove(0));
            count++;
        }

        System.out.println(count);
    }
}
