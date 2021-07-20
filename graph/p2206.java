import java.util.*;
import java.io.*;

public class p2206 {
    static int[][] graph;
    static int[][] visit;
    static int N, M;
    static int route;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public static class Person {
        int x;
        int y;
        int dis;
        int drill;
        public Person(int x, int y, int dis, int drill){
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.drill = drill;
        }
    }

    public static void bfs(int x, int y){
        Queue<Person> q = new LinkedList<>();
        q.add(new Person(x, y, 1, 0));
        visit[y][x] = 0;

        while(!q.isEmpty()){
            Person p = q.poll();

            if(p.x == M-1 && p.y == N-1){
                route = route == -1 ? p.dis : Math.min(route, p.dis);
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx<0 || ny<0 || nx>=M || ny>=N) continue;
                if(visit[ny][nx] <= p.drill) continue;
                if(graph[ny][nx] == 0){
                    visit[ny][nx] = p.drill;
                    q.add(new Person(nx, ny, p.dis + 1, p.drill));
                } else {
                    if(p.drill == 0){
                        visit[ny][nx] = p.drill + 1;
                        q.add(new Person(nx, ny, p.dis + 1, p.drill + 1));
                    }
                }
            }
        }
    }

    // 고려해야할 조건
    // 1. dfs로 풀면 모든 경로를 다 뒤져봐야 하기 때문에 시간초과가 발생한다
    // 2. bfs로 풀면 큐에 다음 경로를 넣을 때 굳이 가보지 않아도 되는 경로는 제외해야 메모리 초과가 발생하지 않는다
    // 3. 특정 좌표에서의 visit은 해당 위치를 지날 때의 공사횟수를 말한다
    // 4. 특정 좌표를 지날 때 해당 위치에서의 공사 횟수와 현재 시행한 공사 횟수가 가지는 의미는 다음과 같다
    //      4-1. 공사를 이미 한 경로는 공사를 아직 안한 경로보다 최단거리로 갈 수 없는 경로이다
    //           반대로 말하면 공사를 이미 한 경로를 지날 때 현재 자신은 아직 공사 횟수가 남아있다면
    //           해당 좌표를 지날 경우 공사를 이미 하고 해당 좌표를 지날때보다 무조건 더 짧은 경로로 지나갈 수 있다
    //      4-2. 특정 좌표에서의 공사 횟수가 현재 공사 횟수와 같다는 말은 해당 좌표를 이미 지나는 경로가 있는데,
    //           그 경로와 지금 경로는 거리적으로 우위가 같기 때문에 굳이 해당 좌표로는 지나갈 필요가 없다
    //      4-3. 따라서 특정 좌표로 가도 되는지를 거를 때는 해당 좌표가 처음 지나가는 길이거나,
    //           아니면 해당 좌표를 경유하는 경로보다 공사 횟수가 많을 때만 갈 수 있도록 하면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visit = new int[N][M];
        route = -1;

        for(int i = 0 ; i < N ; i++){
            int[] input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < M ; j++){
                graph[i][j] = input[j];
                visit[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs(0, 0);        
        System.out.println(route);
    }    
}
