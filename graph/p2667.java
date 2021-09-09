import java.util.*;
import java.io.*;

public class p2667 {
    static int N;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static ArrayList<Integer> list;

    public static class Dot {
        int x;
        int y;
        public Dot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int x, int y){
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x, y));
        int count = 1;
        visit[y][x] = true;

        while(!q.isEmpty()){
            Dot d = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(graph[ny][nx] == 0 || visit[ny][nx]) continue;

                count++;
                visit[ny][nx] = true;
                q.add(new Dot(nx, ny));
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N][N];
        graph = new int[N][N];
        list = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(!visit[i][j] && graph[i][j] == 1) list.add(bfs(j, i));
            }
        }

        Collections.sort(list);
        System.out.println(list.size());

        for(int n : list){
            System.out.println(n);
        }
    }    
}
