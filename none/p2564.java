import java.util.*;
import java.io.*;

public class p2564 {
    static int M, N;
    static int[] left = {0, 3, 4, 2, 1};
    static int[] right = {0, 4, 3, 1, 2};
    static int[] lr;
    static int[] cur;

    public static int rotateRight(int dir, int dis){
        int[] r = {0, 1, -1, -1, 1};
        int sum = 0;
        int cdir = cur[0];
        while(cdir != dir){
            if(cdir <= 2){
                sum += M;
            } else {
                sum += N;
            }
            cdir = right[cdir];
        }
        if(cur[0] != dir){
            sum += r[cdir] == 1 ? dis : lr[cdir] - dis;
            sum -= r[cur[0]] == 1 ? cur[1] : lr[cur[0]] - cur[1];
        } else {
            if(cur[1] * r[cur[0]] < dis * r[cur[0]]){
                sum += Math.abs(cur[1] - dis);
            } else {
                sum += M * 2 + N * 2 - Math.abs(cur[1] - dis);
            }
        }
        System.out.println("right : " + sum);
        return sum;
    }

    public static int rotateLeft(int dir, int dis){
        int[] r = {0, -1, 1, 1, -1};
        int sum = 0;
        int cdir = cur[0];
        while(cdir != dir){
            if(cdir <= 2){
                sum += M;
            } else {
                sum += N;
            }
            cdir = left[cdir];
        }
        if(cur[0] != dir){
            sum += r[cdir] == 1 ? dis : lr[cdir] - dis;
            sum -= r[cur[0]] == 1 ? cur[1] : lr[cur[0]] - cur[1];
        } else {
            if(cur[1] * r[cur[0]] < dis * r[cur[0]]){
                sum += Math.abs(cur[1] - dis);
            } else {
                sum += M * 2 + N * 2 - Math.abs(cur[1] - dis);
            }
        }
        System.out.println("left : " + sum);
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(br.readLine());
        int[][] store = new int[N][2];
        lr = new int[]{0, M, M, N, N};
        for(int i = 0 ; i < T ; i++){
            store[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        cur = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        for(int i = 0 ; i < T ; i++){
            sum += Math.min(rotateLeft(store[i][0], store[i][1]), rotateRight(store[i][0], store[i][1]));
        }
        System.out.println(sum);
    }    
}
