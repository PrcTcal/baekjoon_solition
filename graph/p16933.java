import java.util.*;
import java.io.*;

public class p16933 {
    static int N, M, K, route;
    static int[][] graph;
    static int[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static class Info {
        int x;
        int y;
        int dis;
        int count;
        boolean day;
        public Info(int x, int y, int dis, int count, boolean day){
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.count = count;
            this.day = day;
        }
    }

    public static void bfs(int x, int y){
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(x, y, 1, 0, true));
        while(!q.isEmpty()){
            Info p = q.poll();

            if(p.x == M-1 && p.y == N-1){
                route = route == -1 ? p.dis : Math.min(route, p.dis);
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visit[ny][nx] <= p.count) continue;

                if(graph[ny][nx] == 0){
                    visit[ny][nx] = p.count;
                    q.add(new Info(nx, ny, p.dis + 1, p.count, !p.day));
                } else {
                    if(p.count < K && visit[ny][nx] > p.count + 1 && p.day){
                        visit[ny][nx] = p.count + 1;
                        q.add(new Info(nx, ny, p.dis + 1, p.count + 1, !p.day));
                    } else if(!p.day){
                        q.add(new Info(p.x, p.y, p.dis + 1, p.count, !p.day));
                    }
                }
            }
        }
    }

    // 고려해야할 조건
    // 1. 14442 문제에서 낮밤 개념이 추가되었다
    // 2. 다음 경로에 벽이 없으면 이동하면서 낮밤을 바꿔주는 것 외에는 고려할 사항이 없다
    // 3. 다음 경로에 벽이 있을 경우 낮과 밤일때 조건을 추가해줘야 한다
    //      3-1. 밤일 경우 벽은 부술 수 없으므로 가만히 머물러서 낮으로 바꿔준다. 이때 거리는 1 증가시켜준다
    //      3-2. 낮일 경우 벽을 부술 수 있으므로 기존에 조건으로 주었던 벽을 부순 횟수 체크(K와 비교),
    //            다음 경로로 이동했을 때 벽을 부순 횟수 체크(visit[ny][nx]와 비교),
    //            그리고 낮인지 체크(day와 비교)
    // 4. 낮밤을 가리키는 변수인 day는 큐에 넣을 Info 객체 안에 넣어줬는데, 그 이유는 각 경로마다 낮과 밤이 다를 수 있기 때문에,
    //    즉, 각 경로를 나타내는 Info 객체가 담당해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visit = new int[N][M];
        
        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < M ; j++){
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        
        route = -1;
        visit[0][0] = 0;

        bfs(0, 0);
        System.out.println(route);
    }    
}
