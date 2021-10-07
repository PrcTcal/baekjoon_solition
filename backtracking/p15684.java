import java.util.*;
import java.io.*;

public class p15684 {
    static int N, M, H;
    static int l;
    static int[][] graph;

    public static boolean isMatch(){
        for(int i = 0 ; i < N ; i++){
            int x = i;
            for(int j = 0 ; j < H ; j++){
                if(graph[j][x] == 1){
                    x++;
                } else if(graph[j][x] == -1){
                    x--;
                }
            }
            if(x != i) return false;
        }
        return true;
    }

    // 고려해야할 조건
    // 1. 추가한 사다리의 초기값은 4로 두고 backtracking을 끝냈을 때 값이 3보다 크면 -1로 초기화해야한다.
    // 2. graph 배열에서 -1 오른쪽에 1은 올 수 있지만 현재 index 다음에 1이 있으면 건너뛰어야 한다.
    // 3. 1은 오른쪽으로, -1은 왼쪽으로 움직인다는 의미이다.
    public static void backtracking(int y, int ladder){
        if(ladder > 3) return;
        if(y == H){  
            if(isMatch()){
                l = Math.min(l, ladder);
            }
            return;
        }

        backtracking(y+1, ladder);
        for(int i = 0 ; i < N-1 ; i++){
            if(graph[y][i] == 1 || graph[y][i] == -1) continue;
            if(i < N-1 && graph[y][i + 1] == 1) continue;

            graph[y][i] = 1;
            graph[y][i+1] = -1;
            if(i+1 >= N-2) backtracking(y+1, ladder+1);
            else backtracking(y, ladder+1);
            graph[y][i] = 0;
            graph[y][i+1] = 0;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[H][N];

        for(int i = 0 ; i < M ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[input[0]-1][input[1]-1] = 1;
            graph[input[0]-1][input[1]] = -1;
        }

        l = 4;
        backtracking(0, 0);
        if(l > 3) l = -1;

        System.out.println(l);
    }    
}
