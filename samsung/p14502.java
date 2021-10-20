import java.util.*;
import java.io.*;

public class p14502 {
    static int N, M, safe;
    static int[][] lab;
    static ArrayList<Virus> list;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int max;

    public static class Virus {
        int x;
        int y;
        public Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void buildWall(int depth){
        if(depth == 3){
            int[][] copy = new int[N][M];
            for(int i = 0 ; i < N ; i++){
                copy[i] = lab[i].clone();
            }

            max = Math.max(max, safe - depth - (spreadVirus(copy) - list.size()));
            return;
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(lab[i][j] == 0){
                    lab[i][j] = 1;
                    buildWall(depth + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    public static int spreadVirus(int[][] copy){
        int count = 0;
        Queue<Virus> q = new LinkedList<>();

        for(Virus v : list){
            q.add(v);
        }

        while(!q.isEmpty()){
            Virus v = q.poll();
            count++;
            for(int i = 0 ; i < 4 ; i++){
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(copy[ny][nx] != 0) continue;

                copy[ny][nx] = 2;
                q.add(new Virus(nx, ny));
            }
        }

        return count;
    }

    // 고려해야할 조건
    // 1. DFS를 이용하여 벽을 세운다(depth마다 벽을 하나씩)
    // 2. 벽을 세개 세우면 BFS로 바이러스를 퍼뜨리고, 안전구역의 수를 계산하여 저장한다.
    // 3. 바이러스를 퍼뜨릴 때, 벽을 세운 lab 배열을 그대로 갖다쓰면 복구가 안되므로 lab 배열의 copy를 만들어서 사용한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        list = new ArrayList<>();
        safe = 0;
        max = 0;

        for(int i = 0 ; i < N ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < M ; j++){
                lab[i][j] = input[j];
                if(input[j] == 0) safe++;
                else if(input[j] == 2) list.add(new Virus(j, i));
            }
        }

        buildWall(0);
        System.out.println(max);
    }
}
