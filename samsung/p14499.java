import java.util.*;
import java.io.*;

public class p14499 {
    static int N, M, x, y, K;
    static int[][] board;
    static int[] dice;      // 1 : 윗면, 2 : 아랫면, 3 : 서쪽, 4 : 동쪽, 5 : 북쪽, 6 : 남쪽
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    // 고려해야할 조건
    // 1. 주사위의 회전은 고정된 위치를 나타내는 주사위 배열을 이용하여 생각보다 쉽게 구현할 수 있다.
    public static boolean roll(int dir){
        if(x + dx[dir] >= N || y + dy[dir] >= M || x + dx[dir] < 0 || y + dy[dir] < 0) return false;
        x += dx[dir];
        y += dy[dir];

        int temp = dice[1];
        switch(dir){
            case 1:
                dice[1] = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[4];
                dice[4] = temp;
                break;
            case 2:
                dice[1] = dice[4];
                dice[4] = dice[2];
                dice[2] = dice[3];
                dice[3] = temp;
                break;
            case 3:
                dice[1] = dice[6];
                dice[6] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
                break;
            case 4:
                dice[1] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[6];
                dice[6] = temp;
                break;
        }

        if(board[x][y] == 0){
            board[x][y] = dice[2];
        } else {
            dice[2] = board[x][y];
            board[x][y] = 0;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dice = new int[7];

        for(int i = 0 ; i < N ; i++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        String[] order = br.readLine().split(" ");
        for(int i = 0 ; i < order.length ; i++){
            if(roll(Integer.parseInt(order[i]))) System.out.println(dice[1]);
        }

    }    
}
