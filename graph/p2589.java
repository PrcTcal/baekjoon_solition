import java.util.*;
import java.io.*;

public class p2589 {
    static int N, M;
    static boolean[][] map;
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static class Pos {
        int x;
        int y;
        int dis;
        public Pos(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    public static int bfs(int x, int y){
        Queue<Pos> q = new LinkedList<>();
        int max = 0;
        q.add(new Pos(x, y, 0));
        visit[y][x] = true;
        while(!q.isEmpty()){
            Pos p = q.poll();
            max = Math.max(max, p.dis);
            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N || !map[ny][nx] || visit[ny][nx]) continue;
                visit[ny][nx] = true;
                q.add(new Pos(nx, ny, p.dis+1));
            }
        }
        return max;
    }

    // 고려해야할 조건
    // 1. BFS로 하면 최단거리로 가는 경로의 visit이 먼저 true가 되기 때문에 멀리 돌아가는 길은 끝까지 나아갈 수 없기 때문에 BFS로 풀어야 한다
    // 2. DFS의 경우 visit 배열을 2차원 int 배열로 해서 움직인 거리를 저장하려 했으나 마지막 depth까지 갔을 때를 구분할 수 없기 때문에 visit배열에서
    //    다시 최대값을 탐색해야 하기 때문에 시간초과가 발생한다.
    // 3. x좌표랑 y좌표는 구분 잘하자
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine().replaceAll(" ", "");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = input.charAt(j) == 'W' ? false : true;
            }
        }

        int max = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j]) {
                    visit = new boolean[N][M];
                    max = Math.max(max, bfs(j, i));
                    System.out.println(i + ", " + j + " - " + max);
                }
            }
        }

        System.out.println(max);
    }    
}
