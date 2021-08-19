import java.util.*;
import java.io.*;

public class p9663 {
    static int N, count;
    static int[] queen;     // 인덱스는 열, 해당 인덱스의 값은 행을 나타낸다. ex) queen[0] = 2 -> 2행 0열에 퀸이 위치한다는 뜻

    // 한 열에 퀸이 있으면 그 열에는 더이상 퀸이 위치할 수 없다.
    // 이를 이용하여 depth는 열을 나타내도록 한다
    public static void dfs(int depth){
        // 마지막 열에 퀸을 놨으면 카운트를 하나 증가시켜주자
        if(depth == N){
            count++;
            return;
        } 

        // 현재 열에서 각 행마다 퀸을 하나씩 넣어보고 그 상태에서 다음 열에 놓을 수 있는지를 체크한 후 다음 열로 넘어간다
        for(int i = 0 ; i < N ; i++){
            queen[depth] = i;
            if(isPossible(depth)){
                dfs(depth + 1);
            }
        }
    }
    
    // 해당 열에 퀸을 놓을 수 있는지를 체크한다
    public static boolean isPossible(int col){
        for(int i = 0 ; i < col ; i++){
            if(queen[col] == queen[i]) return false;            // 직선상에서 퀸이 겹치는지 여부 파악
            else if(Math.abs(col - i) == Math.abs(queen[col] - queen[i])) return false;         // 대각선 상에서 퀸이 겹치는지 파악
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = 0;
        queen = new int[N];

        dfs(0);
        System.out.println(count);
    }
}
