import java.util.*;
import java.io.*;

public class p1987 {
    static int R, C, max;
    static int[] alp;
    static char[][] space;
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    // 고려해야할 조건
    // 1. 보드 내 모든 알파벳이 동일하면 출력은 1이어야 한다.
    // 2. 진행하다가 막히면 되돌아가서 다른 경로를 찾아야 하므로 BFS를 쓸 순 없다. DFS에 가까움.
    // 3. 되돌아왔을때 방문여부뿐만 아니라 이미 지나온 알파벳인지 체크하는 배열도 초기화해줘야 한다.
    public static void backtracking(int x, int y, int move){
        visit[y][x] = true;
        alp[space[y][x] - 65]++;

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= C || ny >= R || visit[ny][nx]) continue;
            if(alp[space[ny][nx] - 65] > 0) continue;

            max = Math.max(max, move+1);
            backtracking(nx, ny, move+1);
            visit[ny][nx] = false;
            alp[space[ny][nx]-65]--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        alp = new int[26];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 1;
        space = new char[R][C];
        visit = new boolean[R][C];
        for(int i = 0 ; i < R ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < C ; j++){
                space[i][j] = input.charAt(j);
            }
        }

        backtracking(0, 0, 1);
        System.out.println(max);
    }    
}
