import java.util.*;
import java.io.*;

public class p16946 {
    static int[][] graph, group;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static HashMap<Integer, Integer> map;
    static int N, M;

    // 인접한 0들의 그룹과 그 칸 수를 구한다
    public static int bfs(int x, int y, int cnt){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        group[y][x] = cnt;
        int count = 1;

        while(!q.isEmpty()){
            int[] p = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N || graph[ny][nx] > 0) continue;
                if(group[ny][nx] == 0){
                    group[ny][nx] = cnt;
                    count++;
                    q.add(new int[] {nx, ny});
                }
            }       
        }
        return count;
    }

    // 동일한 그룹이 여러 방향에 인접할 수 있기 때문에 Set을 사용하여 중복을 제거한다
    // 예를 들어 인접한 좌표 중 위쪽과 오른쪽에 있는 공간이 같은 그룹일 수 았다.
    public static int count(int x, int y){
        int count = 1;
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(graph[ny][nx] == 0){
                set.add(group[ny][nx]);
            }
        }

        for(int c : set){
            count += map.get(c);
        }

        return count;
    }

    // 고려해야할 조건
    // 1. 단순히 모든 벽이 있는 좌표에서 bfs를 수행하여 이동 가능한 칸의 수를 구하려고 하면 시간초과가 발생할 수 있다
    // 2. 해당 문제는 뭉쳐있는 빈 벽들을 그룹으로 묶어 각 그룹의 칸 수를 미리 구해놓고, 벽이 있는 좌표에서는 인접한 그룹이 가진
    //    칸의 개수들을 더하면 시간을 절약할 수 있다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        group = new int[N][M];
        map = new HashMap<>();
        int groupNum = 1;

        for(int i = 0 ; i < N ; i++){
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(graph[i][j] == 0 && group[i][j] == 0){
                    map.put(groupNum, bfs(j, i, groupNum));
                    groupNum++;
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(graph[i][j] > 0){
                    sb.append(count(j,i) % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
