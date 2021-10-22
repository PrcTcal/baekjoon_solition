import java.util.*;
import java.io.*;

public class p15685 {
    static int N;
    static boolean[][] graph = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void drawDragonCurve(int x, int y, int d, int g){
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        int gen = 0;
        q.add(d);

        // g세대 드래곤커브를 그리기 위해 (x,y)에서부터 이동경로를 큐에 넣는다. 
        while(gen < g){
            for(int i = 0 ; i < q.size() ; i++){
                int dir = q.poll();
                s.push((dir + 1) % 4);
                q.add(dir);
            }
            while(!s.isEmpty()){
                q.add(s.pop());
            }
            gen++;
        }
        
        // (x,y)에서부터 큐에 있는 이동경로를 따라 드래곤 커브를 그린다.
        int nx = x;
        int ny = y;
        graph[y][x] = true;
        while(!q.isEmpty()){
            int dir = q.poll();
            nx += dx[dir];
            ny += dy[dir];
            if(nx >= 0 && ny >= 0 && nx < 101 && ny < 101){
                graph[ny][nx] = true;
            }
        }
    }

    // 1칸짜리 정사각형의 개수 구하기
    public static int count(){
        int cnt = 0;
        for(int i = 0 ; i < 100 ; i++){
            for(int j = 0 ; j < 100 ; j++){
                int x = j;
                int y = i;
                int nx = j+1;
                int ny = i+1;
                if(graph[y][x] && graph[y][nx] && graph[ny][x] && graph[ny][nx]) cnt++;
            }
        }
        return cnt;
    }

    // 고려해야할 조건
    // 1. 다음 세대의 드래곤커브는 (x,y)에서부터 이전 세대를 그리는 방향을 이전 세대의 끝점에서부터 역순으로 그려주면 된다.
    // 2. 정순의 이동경로는 큐에, 90도로 회전한 정순은 스택에 저장하고, 이전 세대를 한번 순회한 뒤에 스택에 있는 이동경로를 큐에 넣어주면 
    //    큐에는 (이전 세대의 이동 경로) + (다음 세대를 그리기 위한 이동 경로)가 저장되고 이는 곧 g세대의 드래곤 커브이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            drawDragonCurve(input[0], input[1], input[2], input[3]);
        }

        System.out.println(count());
    }    
}
