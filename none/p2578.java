import java.util.*;
import java.io.*;

public class p2578 {
    static int[][] board;
    static int[][] bingo;
    static int[] cross;

    public static void Bingo(int[] input){
        int count = 3;
        int t = 0;
        for(int i = 0 ; i < input.length ; i++){
            t++;
            int num = input[i];
            int row = board[0][num];
            int col = board[1][num];

            if (row == col) {
                cross[0]--;
                if (row == 2)
                    cross[1]--;
                if (cross[0] == 0)
                    count--;
            }

            if (row + col == 4) {
                cross[1]--;
                if (row == 2)
                    cross[0]--;
                if (cross[1] == 0)
                    count--;
            }

            bingo[0][row]--;
            if (bingo[0][row] == 0) count--;
            bingo[1][col]--;
            if (bingo[1][col] == 0) count--;

            if (count <= 0) {
                System.out.println(t);
                break;
            }
        }
    }

    // 미해결 문제(추후 해결)
    // 원인 : 테스트케이스 오답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        board = new int[2][26];
        bingo = new int[2][5];
        cross = new int[2];

        for(int i = 0 ; i < 5 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < 5 ; j++){
                int idx = Integer.parseInt(st.nextToken());
                board[0][idx] = j;
                board[1][idx] = i;
            }
        }

        for(int i = 0 ; i < 2 ; i++){
            cross[i] = 5;
            for(int j = 0 ; j < 5 ; j++){
                bingo[i][j] = 5;
            }
        }
        int[] input = new int[25];
        int n = 0;
        // i : 행, j : 열
        for(int i = 0 ; i < 5 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()){
                input[n++] = Integer.parseInt(st.nextToken());
            }
        }
        Bingo(input);
        br.close();
    }
}
