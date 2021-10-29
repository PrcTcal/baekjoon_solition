import java.util.*;
import java.io.*;

public class p2573 {
    static int N, M;
    static int[][] space;
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static class Ice {
        int x;
        int y;
        int shrink;
        public Ice(int x, int y, int shrink){
            this.x = x;
            this.y = y;
            this.shrink = shrink;
        }
    }

    public static void bfs(int x, int y){
        Queue<Ice> q = new LinkedList<>();
        q.add(new Ice(x, y, 0));
        visit[y][x] = true;
        while(!q.isEmpty()){
            Ice c = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N || visit[ny][nx]) continue;
                if(space[ny][nx] > 0){
                    q.add(new Ice(nx, ny, 0));
                    visit[ny][nx] = true;
                } else {
                    if(space[c.y][c.x] > 0) space[c.y][c.x]--;
                }
            } 
        }
    }

    // 고려해야할 조건
    // 1. BFS를 수행하며 인접한 칸의 값이 0이어도 이번년도에서 녹아서 바다가 된 케이스가 있을 수 있으므로 visit이 true라면 빙산의 값을 뺴주지 않는다
    // 2. 처음엔 빙산 높이가 줄어들 칸의 좌표와 줄어들 값을 따로 큐에 저장해놓고 탐색이 끝난 후에 일괄적으로 빼줬으나 그럴 경우 시간초과가 발생함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            space[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean flag = true;
        int year = 0;
        while(flag){
            boolean once = false;
            visit = new boolean[N][M];
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(space[i][j] > 0){
                        if(!once && !visit[i][j]){
                            bfs(j, i);
                            once = true;
                        } else if(once && !visit[i][j]){
                            flag = false;
                        }
                    }
                }
            }

            if(once && flag) {
                year++;
            }else if(!once){
                year = 0;
                flag = false;
            }
        }

        System.out.println(year);
    }    
}
