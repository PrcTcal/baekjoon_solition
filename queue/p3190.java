import java.util.*;
import java.io.*;

public class p3190 {
    static int[][] board;       // 빈 공간 : 0, 사과 : 1, 뱀 : 2
    static int N, K;
    static Queue<String[]> queue;
    static int[] dirX = {0,1,0,-1};         // D면 idx 1 증가, L이면 idx 1 감소
    static int[] dirY = {1,0,-1,0};         // D면 idx 1 증가, L이면 idx 1 감소

    public static int calcTurnIdx(int dirIdx, String turn){
        int idx;
        if(turn.equals("D")){
            idx = dirIdx + 1 == 4 ? 0 : dirIdx + 1;
        } else {
            idx = dirIdx - 1 == -1 ? 3 : dirIdx - 1;
        }
        return idx;
    }

    public static boolean isMovable(int x, int y, int idx){
        boolean check = false;
        if(x + dirX[idx] >= 0 && x + dirX[idx] < N){
            if(y + dirY[idx] >= 0 && y + dirY[idx] < N){
                if(board[x + dirX[idx]][y + dirY[idx]] != 2){
                    check = true;
                }
            }
        }
        return check;
    }

    public static boolean isApple(int x, int y, int idx){
        boolean apple = false;
        if(board[x + dirX[idx]][y + dirY[idx]] == 1){
            apple = true;
        }
        return apple;
    }

    public static void print(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public static void solution(int posx, int posy){
        int t = 0;
        String[] tmp = queue.poll();
        int dirIdx = 0;
        int turnTime = Integer.parseInt(tmp[0]);
        String turnDir = tmp[1];
        Queue<int[]> snake = new LinkedList<>();
        snake.add(new int[] { posx, posy });
        while(true){
            print();
            t++;     
            // 다음번에 이동할 위치에서 충돌이 안일어날 경우
            if(isMovable(posx, posy, dirIdx)){
                if(!isApple(posx, posy, dirIdx)){
                    int[] tail = snake.poll();
                    board[tail[0]][tail[1]] = 0;
                }
                board[posx + dirX[dirIdx]][posy + dirY[dirIdx]] = 2;
                snake.add(new int[]{posx + dirX[dirIdx], posy + dirY[dirIdx]});
                posx += dirX[dirIdx];
                posy += dirY[dirIdx];

                if(t == turnTime){
                    dirIdx = calcTurnIdx(dirIdx, turnDir);
                    if(!queue.isEmpty()){
                        tmp = queue.poll();
                        turnTime = Integer.parseInt(tmp[0]);
                        turnDir = tmp[1];
                    }
                }
            // 다음번에 이동할 위치에서 충돌이 일어날 경우
            } else {
                break;
            }
        }
        System.out.println(t);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        board[0][0] = 2;
        K = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < K ; i++){
            String[] pos = br.readLine().split(" ");
            board[Integer.parseInt(pos[0]) - 1][Integer.parseInt(pos[1]) - 1] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();

        for(int i = 0 ; i < L ; i++){
            queue.add(br.readLine().split(" "));
        }

        solution(0,0);
    }    
}
