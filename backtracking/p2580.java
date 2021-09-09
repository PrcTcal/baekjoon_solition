import java.util.*;
import java.io.*;

public class p2580 {
    static int[][] map;
    static StringBuilder sb;

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
        if(col == 9){
            backtracking(row + 1, 0);
            return;
        }

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

        if(map[row][col] == 0){
            for(int num = 1 ; num <= 9 ; num++){
                if(isPossible(row, col, num)){
                    map[row][col] = num;
                    backtracking(row, col + 1);
                }
            }
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
