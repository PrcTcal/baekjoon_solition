import java.util.*;
import java.io.*;

public class p14503 {
    static int N, M;
    static int[][] space;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int dis;
    static Queue<Robot> q;

    public static class Robot {
        int x;
        int y;
        int dir;
        public Robot(int x, int y, int dir){
            this.x =  x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void operate(){
        while(!q.isEmpty()){
            Robot r = q.poll();
            if(space[r.y][r.x] == 0){
                space[r.y][r.x] = 2;
                dis++;
            }

            boolean flag = false;
            for(int i = 0 ; i < 4 ; i++){
                r.dir = (r.dir + 3) % 4;
                int nx = r.x + dx[r.dir];
                int ny = r.y + dy[r.dir];

                if(space[ny][nx] == 0){
                    r.x = nx;
                    r.y = ny;
                    q.add(r);
                    flag = true;
                    break;
                }
            }

            if(!flag){
                int opp = (r.dir + 2) % 4;
                if(space[r.y + dy[opp]][r.x + dx[opp]] == 2){
                    r.x += dx[opp];
                    r.y += dy[opp];
                    q.add(r);
                }
            }
        }   
    }

    // 고려해야할 조건
    // 1. 현재 위치에서 인접한 칸의 상태에 따라 이동할 위치가 달리지기 때문에 dfs나 bfs로 구현할 수 없다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];
        st = new StringTokenizer(br.readLine(), " ");
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        Robot r = new Robot(x, y, dir);
        q = new LinkedList<>();
        dis = 0;

        for(int i = 0 ; i < N ; i++){
            space[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        q.add(r);
        operate();
        System.out.println(dis);

    }    
}
