import java.util.*;
import java.io.*;
public class p10026 {
    static int N;
    static int[][] graph;       // 0 : R, 1 : G, 2 : B
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static boolean isSame(int x, int y, int color, boolean disorder){
        if(disorder){
            if((color < 2 && graph[y][x] < 2) || graph[y][x] == color){
                return true;
            }
        } else {
            if(graph[y][x] == color){
                return true;
            }
        }
        return false;
    }

    public static void bfs(int x, int y, boolean d){
        Queue<Integer> q = new LinkedList<>();
        q.add(graph[y][x]);
        visit[y][x] = true;
        while(!q.isEmpty()){
            int c = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) continue;
                if(isSame(nx, ny, c, d)){
                    visit[ny][nx] = true;
                    bfs(nx, ny, d);
                }
            }
        }
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int count = 0;
        graph = new int[N][N];
        visit = new boolean[N][N];

        for(int i = 0 ; i < N ; i++){
            String[] input = br.readLine().split("");
            for(int j = 0 ; j < N ; j++){
                switch(input[j]){
                    case "R":
                        graph[i][j] = 0;
                        break;
                    case "G":
                        graph[i][j] = 1;
                        break;
                    case "B":
                        graph[i][j] = 2;
                        break;
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(!visit[i][j]) {
                    bfs(j,i, false);
                    count++;
                }
            }
        }
        sb.append(count).append(" ");
        count = 0;
        visit = new boolean[N][N];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(!visit[i][j]){
                    bfs(j, i, true);
                    count++;
                }
            }
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
