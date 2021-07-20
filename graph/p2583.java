import java.util.*;
import java.io.*;

public class p2583 {
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int area;
    static int M, N;

    public static void insert(int x1, int y1, int x2, int y2){
        for(int i = y1 ; i < y2 ; i++){
            for(int j = x1 ; j < x2 ; j++){
                graph[i][j] = 1;
            }
        }
    }

    public static void dfs(int x, int y){
        visit[y][x] = true;
        area++;

        for(int i = 0 ; i < 4 ; i++){
            if(x+dx[i] >= 0 && x+dx[i] < N && y+dy[i] >= 0 && y+dy[i] < M){
                if(graph[y+dy[i]][x+dx[i]] == 0 && !visit[y+dy[i]][x+dx[i]]){
                    dfs(x+dx[i], y+dy[i]);
                }
            }
        }
    }

    // 고려해야할 조건
    // 1. 색칠이 안된 영역을 기준으로 dfs를 수행해야 한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        graph = new int[M][N];
        visit = new boolean[M][N];

        for(int i = 0 ; i < K ; i++){
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            insert(s[0], s[1], s[2], s[3]);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                if(graph[i][j] == 0 && !visit[i][j]){
                    area = 0;
                    dfs(j, i);
                    result.add(area);
                }
            }
        }

        sb.append(result.size() + "\n");
        Collections.sort(result);
        for(int a : result){
            sb.append(a + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
