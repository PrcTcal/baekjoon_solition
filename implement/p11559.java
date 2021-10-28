import java.util.*;
import java.io.*;

public class p11559 {
    static int combo;
    static char[][] field;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visit;

    public static class Puyo {
        int x;
        int y;
        char color;
        public Puyo(int x, int y, char color){
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static int fall(int x, int y){
        int count = 0;
        int py = y;
        int ny = y + 1;
        while(ny < 12 && field[ny][x] == '.'){
            field[ny][x] = field[py][x];
            field[py][x] = '.';
            count++;
            ny++;
            py++;
        }
        return count;
    }

    public static boolean isFinish(){
        boolean result = true;
        for(int i = 11 ; i >= 0 ; i--){
            for(int j = 0 ; j < 6 ; j++){
                if(field[i][j] != '.'){
                    if(fall(j, i) > 0) result = false;
                }
            }
        }
        return result;
    }

    public static boolean bfs(int x, int y){
        Queue<Puyo> q = new LinkedList<>();
        Queue<Puyo> copy = new LinkedList<>();
        boolean explode = false;
        q.add(new Puyo(x, y, field[y][x]));
        copy.add(q.peek());
        visit[y][x] = true;

        while(!q.isEmpty()){
            Puyo p = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= 6 || ny >= 12 || visit[ny][nx]) continue;
                if(field[ny][nx] == p.color){
                    Puyo t = new Puyo(nx, ny, field[ny][nx]);
                    visit[ny][nx] = true;
                    copy.add(t);
                    q.add(t);
                }
            }
        }

        if(copy.size() >= 4){
            while(!copy.isEmpty()){
                Puyo t = copy.poll();
                field[t.y][t.x] = '.';
            }
            explode = true;
        }
        return explode;
    }

    // 고려해야할 조건
    // 1. 매번 터질 수 있는 뿌요들을 모두 터뜨린 후에 공중에 떠있는 뿌요들을 아래로 내려야 한다
    // 2. 만약 터질 수 있는 그룹이 여러개 있더라도 연쇄는 1만 증가한다
    // 3. 게임이 끝나는 조건은 연쇄가 더 이상 발생하지 않을때인데, 연쇄가 발생하지 않으면 떨어지는 뿌요도 없으므로 떨어진 뿌요가 있는지 여부로
    //    게임을 끝내면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[12][6];
        combo = 0;

        for(int i = 0 ; i < 12 ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < 6 ; j++){
                field[i][j] = input.charAt(j);
            }
        }

        boolean end = false;
        boolean explode = false;
        while(!end){
            for(int i = 11 ; i >= 0 ; i--){
                for(int j = 0 ; j < 6 ; j++){
                    if(field[i][j] != '.'){
                        visit = new boolean[12][6];
                        if(bfs(j, i)){
                            if(!explode) explode = true;
                        }
                    }
                }
            }

            if(explode) combo++;
            explode = false;

            end = isFinish();
        }

        System.out.println(combo);
    }
}
