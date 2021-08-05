import java.util.*;
import java.io.*;

public class p2116 {
    static int N;
    static int[][] arr;

    public static int getSide(int b, int t){
        int max = 6;
        if(Math.max(b, t) == 6) {
            max = Math.min(b, t) == 5 ? 4 : 5;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[10000][6];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][4] = Integer.parseInt(st.nextToken());
            arr[i][5] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for(int i = 0 ; i < 6 ; i++){
            int bottom = arr[0][i];
            int top = arr[0][(i+3) % 6];
            int sum = getSide(bottom, top);
            for(int j = 1 ; j < N ; j++){
                for(int k = 0 ; k < 6 ; k++){
                    if(arr[j][k] == top){
                        bottom = arr[j][k];
                        top = arr[j][(k+3) % 6];
                        break;
                    }
                }
                sum += getSide(bottom, top);
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }    
}
