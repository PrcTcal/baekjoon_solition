import java.util.*;
import java.io.*;

public class p2580 {
    static int[][] map;
    static StringBuilder sb;

    // 가로, 세로, 블럭에 같은 숫자가 있는지 체크
    public static boolean isPossible(int row, int col, int val){
        for(int i = 0 ; i < 9 ; i++){
            if(map[i][col] == val) return false;
            if(map[row][i] == val) return false;
        }

        int rowidx = (row / 3) * 3;
        int colidx = (col / 3) * 3;
        for(int i = rowidx ; i < rowidx+3 ; i++){
            for(int j = colidx ; j < colidx+3 ; j++){
                if(map[i][j] == val) return false;
            }
        }
        return true;
    }

    public static void backtracking(int row, int col){
        // (0,0)에서 시작하여 한 행을 다 체크했으면 다음 행으로 넘어감
        if(col == 9){
            backtracking(row + 1, 0);
            return;
        }

        // 모든 행을 다 체크했으면 출력 후 종료
        // 프로그램을 종료하지 않으면 다른 경우의 스도쿠도 출력해버림
        if(row == 9){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        // 빈칸일때 숫자가 들어갈 수 있는지 체크 후 다음 depth로 넘어감
        if(map[row][col] == 0){
            for(int num = 1 ; num <= 9 ; num++){
                if(isPossible(row, col, num)){
                    map[row][col] = num;
                    backtracking(row, col + 1);
                }
            }
            // 이전 depth로 돌아가기 전에 빈칸이었던 칸을 초기화해야 이전 depth에서 다음 숫자를 넣고 난 후
            // 다음 depth로 넘어갔을 때 해당 칸이 빈칸인채로 존재할 수 있다.
            map[row][col] = 0;
            return;
        }

        backtracking(row, col + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        map = new int[9][9];
        for(int i = 0 ; i < 9 ; i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        backtracking(0, 0);
    }    
}
