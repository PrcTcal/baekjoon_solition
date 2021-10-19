import java.util.*;
import java.io.*;

public class p12100 {
    static int N;
    static int[][] next;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int max;

    public static void move(int dir){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < N ; i++){
            if(dx[dir] == 0){
                int m = dy[dir] > 0 ? N-1 : 0;

                if(dy[dir] > 0){
                    for(int j = N-1 ; j >= 0 ; j--){
                        if(next[j][i] > 0){
                            q.add(next[j][i]);
                            next[j][i] = 0;
                        }
                    }

                    while(!q.isEmpty()){
                        int num = q.poll();
                        if(!q.isEmpty() && num == q.peek()){
                            num *= 2;
                            q.poll();
                        }
                        next[m--][i] = num;
                    }

                } else {
                    for(int j = 0 ; j < N ; j++){
                        if(next[j][i] > 0){
                            q.add(next[j][i]);
                            next[j][i] = 0;
                        }
                    }

                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (!q.isEmpty() && num == q.peek()) {
                            num *= 2;
                            q.poll();
                        }
                        next[m++][i] = num;
                    }
                }
            } else {
                int m = dx[dir] > 0 ? N - 1 : 0;

                if (dx[dir] > 0) {
                    for (int j = N - 1; j >= 0; j--) {
                        if(next[i][j] > 0){
                            q.add(next[i][j]);
                            next[i][j] = 0;
                        }
                    }

                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (!q.isEmpty() && num == q.peek()) {
                            num *= 2;
                            q.poll();
                        }
                        next[i][m--] = num;
                    }

                } else {
                    for (int j = 0; j < N; j++) {
                        if(next[i][j] > 0){
                            q.add(next[i][j]);
                            next[i][j] = 0;
                        }
                    }

                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (!q.isEmpty() && num == q.peek()) {
                            num *= 2;
                            q.poll();
                        }
                        next[i][m++] = num;
                    }
                }
            }
        }
    }

    public static void findMax(int[][] board){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                max = Math.max(max, board[i][j]);
            }
        }
    }

    // 고려해야할 조건
    // 1. 상하좌우 방향별로 블럭을 다뤄야 하는 순서가 조금 다르다. 예를 들어 위로 움직일때는 가장 위에 있는 블럭부터 합쳐야 하지만
    //    하로 움직일 경우 가장 아래에 있는 블럭부터 합쳐야 한다.
    // 2. 한번 합친 블럭은 해당 움직임에서 다시 합쳐질 수 없기 때문에 Queue를 만들고 먼저 다룰 블럭부터 순서대로 합치거나 합치지 않은
    //    후에 queue에서 빼줘야 한다.
    // 3. 백트래킹에서 depth가 깊어졌다가 돌아올 때 이전 움직임을 복구해야 하는데, 단순히 board = next.clone()을 사용하면 board가
    //    next의 주소값을 참조하기 때문에 next의 값이 바뀌면 board의 값도 바뀌기 때문에 board는 next의 주소를 참조하지 않게 하는게 중요하다
    public static void backtracking(int move){
        if(move >= 5){
            findMax(next);
            return;
        }
        
        int[][] board = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            board[i] = next[i].clone();
        }

        for(int i = 0 ; i < 4 ; i++){
            move(i);
            backtracking(move+1);
            for(int j = 0 ; j < N ; j++){
                next[j] = board[j].clone();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        next = new int[N][N];
        max = 0;

        for(int i = 0 ; i < N ; i++){
            next[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        backtracking(0);
        System.out.println(max);
    }

}
