import java.util.*;
import java.io.*;

public class p4963 {
    static int[][] graph;
    static boolean[][] visit;
    static int w, h;
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void dfs(int x, int y){
        visit[x][y] = true;

        for(int i = 0 ; i < 8 ; i++){
            int a = dy[i];
            int b = dx[i];
            if(x+a >= 0 && x+a < h && y+b >= 0 && y+b < w){
                if(graph[x + a][y + b] == 1 && !visit[x + a][y + b]){
                    dfs(x+a, y+b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String input;
        while(!(input = br.readLine()).equals("0 0")){
            String[] tmp = input.split(" ");
            w = Integer.parseInt(tmp[0]);
            h = Integer.parseInt(tmp[1]);
            graph = new int[h][w];
            visit = new boolean[h][w];

            for(int i = 0 ; i < h ; i++){
                graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int count = 0;
            for(int i = 0 ; i < h ; i++){
                for(int j = 0 ; j < w ; j++){
                    if(graph[i][j] == 1 && !visit[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            sb.append(count + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
