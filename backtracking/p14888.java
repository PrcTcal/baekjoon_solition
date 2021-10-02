import java.util.*;
import java.io.*;

public class p14888 {
    static int N, min, max;
    static int[] arr;
    static int[] oper;

    public static void backtracking(int idx, int result){
        if(idx == N){
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            if(oper[i] > 0){
                oper[i]--;
                switch(i){
                    case 0:
                        backtracking(idx+1, result + arr[idx]);
                        break;
                    case 1:
                        backtracking(idx + 1, result - arr[idx]);
                        break;
                    case 2:
                        backtracking(idx + 1, result * arr[idx]);
                        break;
                    case 3:
                        if(result >= 0){
                            backtracking(idx + 1, result / arr[idx]);
                        } else {
                            backtracking(idx + 1, (Math.abs(result) / arr[idx]) * -1);
                        }
                        break;
                }
                oper[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        oper = new int[4];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i < 4 ; i++){
            int op = Integer.parseInt(st.nextToken());
            oper[i] = op;
        }

        backtracking(1, arr[0]);
        System.out.println(max + "\n" + min);
    }    
}
