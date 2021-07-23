import java.util.*;
import java.io.*;

public class p14442 {
    static int[][] graph;
    static int[][] visit;     // y축, x축, 방문여부(0 : 방문x, 1 : 방문o)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M, K, route;

    public static class Person {
        int x;
        int y;
        int dis;
        int count;
        public Person(int x, int y, int dis, int count){
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.count = count;
        }
    }

    public static void bfs(int x, int y){
        Queue<Person> q = new LinkedList<>();
        q.add(new Person(x, y, 1, 0));
        while(!q.isEmpty()){
            Person p = q.poll();

            if(p.x == M-1 && p.y == N-1){
                route = route == -1 ? p.dis : Math.min(route, p.dis);
                return;
            }
            
            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visit[ny][nx] <= p.count) continue;
                if(graph[ny][nx] == 0){
                    visit[ny][nx] = p.count;
                    q.add(new Person(nx, ny, p.dis + 1, p.count));
                } else {
                    if(p.count < K && visit[ny][nx] > p.count + 1){
                        visit[ny][nx] = p.count + 1;
                        q.add(new Person(nx, ny, p.dis + 1, p.count + 1));
                    }
                }
            }
        }
    }

    // 고려해야할 조건
    // 1. 2206 문제와 같이 queue에 너무 많은 경로가 쌓이지 않도록 해야한다
    // 2. 2206과 다른점은 최대 K번까지 벽을 부술 수 있다는 점인데, 이 때문에 다음번에 갈 경로에서의 벽을 부순 횟수가
    //    현재 벽을 부순 횟수 + 1보다 커야한다는 조건이 추가되어야 한다
    //    자세히 설명하자면 내가 다음에 가려는 곳은 벽이 있고, 해당 경로는 벽을 부순 횟수가 2이며, 현재 나는 벽을 1번 부수고 왔다고 가정하자
    //    이 경우 다음 경로로 이동할 경우 해당 경로로 이동했을 때의 벽을 부순 횟수는 똑같이 2가 되어버린다. 즉, 해당 경로로 가는 이점이 없어진다는 것이므로
    //    해당 경로는 굳이 큐에 추가하지 않아도 된다. 이 조건을 잡아주지 않으면 시간초과가 발생할 수 있다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visit = new int[N][M];
        route = -1;

        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < M ; j++){
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        visit[0][0] = 0;

        bfs(0, 0);
        System.out.println(route);
    }
}
