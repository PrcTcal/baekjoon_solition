import java.util.*;
import java.io.*;

public class p10157 {
    static int[][] seat;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int C, R;

    public static boolean checkRange(int r, int c, int idx){
        boolean result = false;
        if(r + dir[idx][0] < 0 || r + dir[idx][0] >= R) result = true;
        if(c + dir[idx][1] < 0 || c + dir[idx][1] >= C) result = true;
        return result;
    }

    // 고려해야할 조건
    // 1. 범위 체크(주어진 C*R 이차원 배열 내에서 인덱스가 움직여야 한다)
    // 2. 방향 전환(좌석을 배정하다가 막히면 방향을 바꿔줘야함)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        seat = new int[R][C];
        int col = 0, row = 0;
        int idx = 0;

        for(int n = 1 ; n <= K ; n++){
            seat[row][col] = n;

            if(n == C * R && n < K) {
                System.out.println(0);
                break;
            } else if(n == K){
                System.out.println((col+1) + " " + (row+1));
            }

            if(checkRange(row, col, idx) || seat[row + dir[idx][0]][col + dir[idx][1]] != 0){
                idx = (idx + 1) % 4;
            }

            row += dir[idx][0];
            col += dir[idx][1];
        }
    }
}
