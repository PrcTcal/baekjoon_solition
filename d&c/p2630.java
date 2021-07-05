import java.util.*;
import java.io.*;

public class p2630 {
    static int[][] square;
    static int blue;
    static int white;

    public static void solution(int n, int x, int y){
        boolean isSame = true;
        for(int i = y ; i < y + n ; i++){
            if(isSame){
                for(int j = x ; j < x + n ; j++){
                    if(square[i][j] != square[y][x]){
                        isSame = false;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if(isSame){
            if(square[y][x] == 1){
                blue++;
            } else {
                white++;
            }
        } else {
            solution(n / 2, x, y);
            solution(n / 2, x + (n / 2), y);
            solution(n / 2, x, y + (n / 2));
            solution(n / 2, x + (n / 2), y + (n / 2));
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        square = new int[N][N];
        blue = white = 0;

        for(int i = 0 ; i < N ; i++){
            square[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
        solution(N, 0, 0);
        System.out.println(white + "\n" + blue);
    }    
}
