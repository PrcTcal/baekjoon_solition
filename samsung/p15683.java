import java.util.*;
import java.io.*;

public class p15683 {
    static int N, M;
    static char[][] office;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<CCTV> list;
    static int min;

    public static class CCTV {
        int x;
        int y;
        int type;
        public CCTV(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    // 사각지대 넓이 구하기
    public static int getEmptySpace(){
        int count = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(office[i][j] == '0') count++;
            }
        }
        return count;
    }

    // CCTV가 현재 바라보는 방향 기준으로 감시 범위 채우기
    public static void setCCTV(CCTV c, int dir){
        ArrayList<Integer> dirList = new ArrayList<>();

        dirList.add(dir);
        if(c.type == 2){
            dirList.add(dir+2);
        } else {
            for(int i = 0 ; i < c.type - 2 ; i++){
                dirList.add(dir + (i+1));
            }
        }

        for(int d : dirList){
            int nx = c.x + dx[d % 4];
            int ny = c.y + dy[d % 4];
            while(nx >= 0 && ny >= 0 && nx < M && ny < N && office[ny][nx] != '6'){
                office[ny][nx] = '#';
                nx += dx[d % 4];
                ny += dy[d % 4];
            }
        }
    }

    public static void dfs(int idx, int count){
        if(count > 8 || list.size() == idx){
            min = Math.min(min, getEmptySpace());
            return;
        }

        char[][] copy = new char[N][M];
        for(int i = 0 ; i < N ; i++){
            copy[i] = office[i].clone();
        }

        if(list.get(idx).type != 5){
            for(int i = 0 ; i < 4 ; i++){
                setCCTV(list.get(idx), i);
                dfs(idx+1, count+1);
                for (int j = 0; j < N; j++) {
                    office[j] = copy[j].clone();
                }
            }
        } else {
            setCCTV(list.get(idx), 0);
            dfs(idx+1, count+1);
            for (int i = 0; i < N; i++) {
                office[i] = copy[i].clone();
            }
        } 
    }

    // 고려해야할 조건
    // 1. DFS로 각 카메라의 방향별로 사각지대의 크기를 구해서 최소값을 저장한다.
    // 2. 5번 카메라의 경우 딱히 회전을 할 필요가 없다.
    // 3. 2번 카메라의 경우도 상하나 좌우 하나씩만 하게 만드는게 좋지만 굳이 하진 않았다(4방향 다 돌려보게 구현함)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new char[N][M];
        list = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine().replaceAll(" ", "");
            for(int j = 0 ; j < M ; j++){
                office[i][j] = input.charAt(j);
                if(office[i][j] != '0' && office[i][j] != '6') list.add(new CCTV(j, i, office[i][j] - 48));
            }
        }

        dfs(0, 0);
        System.out.println(min);

    }    
}
