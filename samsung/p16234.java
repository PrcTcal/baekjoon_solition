import java.util.*;
import java.io.*;

public class p16234 {
    static int N, L, R;
    static int[][] graph;
    static boolean[][] visit;
    static boolean flag;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static class Pos {
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean bfs(int x, int y){
        int sum = 0;
        Queue<Pos> q = new LinkedList<>();
        ArrayList<Pos> list = new ArrayList<>();
        q.add(new Pos(x, y));
        visit[y][x] = true;
        while(!q.isEmpty()){
            Pos p = q.poll();
            list.add(p);
            sum += graph[p.y][p.x];

            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) continue;
                int dif = Math.abs(graph[ny][nx] - graph[p.y][p.x]);
                if(dif < L || dif > R) continue;

                visit[ny][nx] = true;
                q.add(new Pos(nx, ny));
            }
        }

        if(list.size() > 1){
            sum /= list.size();
            for(Pos p : list){
                graph[p.y][p.x] = sum;
            }
            return true;
        }
        return false;
    }

    // 고려해야할 조건
    // 1. visit 배열은 한번 그래프를 전부 탐색할 때마다 초기화해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        flag = false;
        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int count = 0;
        while(true){
            visit = new boolean[N][N];
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(!visit[i][j]){
                        if(bfs(j, i)) flag = true;
                    }
                }
            }

            if(!flag) break;
            count++;
            flag = false;
        }
        
        System.out.println(count);
    }
      
}
