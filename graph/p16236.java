import java.util.*;
import java.io.*;

public class p16236 {
    static int[][] graph, check;        // 지도와 
    static int[] dx = {0, 0, -1, 1};    // x축 이동
    static int[] dy = {-1, 1, 0, 0};    // y축 이동
    static int min_dist, min_x, min_y;
    static Shark shark;
    static int N;

    public static class Shark{
        int x;
        int y;
        int size;
        int count;
        int time;
        public Shark(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
            this.count = size;
            this.time = 0;
        }
    }

    public static class Info{
        int x;
        int y;
        public Info(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void init(){
        min_dist = 401;
        min_x = min_y = 21;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                check[i][j] = -1;
            }
        }
    }

    public static void bfs(int x, int y){
        Queue<Info> q = new LinkedList<>();
        check[y][x] = 0;
        q.add(new Info(x, y));

        while(!q.isEmpty()){
            Info cur = q.poll();
            x = cur.x;
            y = cur.y;
            for(int i = 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(check[ny][nx] != -1 || graph[ny][nx] > shark.size) continue;

                check[ny][nx] = check[y][x] + 1;

                if(graph[ny][nx] != 0 && graph[ny][nx] < shark.size){
                    if(min_dist > check[ny][nx]){
                        min_x = nx;
                        min_y = ny;
                        min_dist = check[ny][nx];
                    } else if(min_dist == check[ny][nx]){
                        if(min_y == ny){
                            if(min_x > nx){
                                min_x = nx;
                                min_y = ny;
                            }
                        } else if(min_y > ny){
                            min_x = nx;
                            min_y = ny;
                        }
                    }
                }

                q.add(new Info(nx, ny));
            }
        }
    }

    // 고려해야할 조건
    // 1. 현 위치에서 모든 물고기로의 거리 중에서 최단 거리를 찾아야 한다(BFS)
    // 2. 큐에는 현재 위치를 넣고, 현재 위치 기준으로 상하좌우로 이동할 좌표를 다음에 넣는다
    // 3. 최단거리에 위치한 물고기가 여러마리일 때 y좌표가 작은게 먼저, 같다면 x좌표가 작은 물고기를 min 변수에 저장한다
    // 4. 매 bfs는 한마리의 물고기를 먹는 과정이다. 따라서 한번 bfs를 수행하여 한마리를 먹었다면 check 배열을 다시 초기화해야 한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        check = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                int f = Integer.parseInt(st.nextToken());

                if(f == 9){
                    shark = new Shark(j, i, 2);
                } else {
                    graph[i][j] = f;
                }
            }
        }
        while(true){
            init();
            bfs(shark.x, shark.y);

            if(min_x != 21 && min_y != 21){
                shark.time += check[min_y][min_x];
                shark.count--;
                if(shark.count == 0){
                    shark.size++;
                    shark.count = shark.size;
                }
    
                graph[min_y][min_x] = 0;
                shark.x = min_x;
                shark.y = min_y;
            } else {
                break;
            }
        }
        System.out.println(shark.time);
    }
}
