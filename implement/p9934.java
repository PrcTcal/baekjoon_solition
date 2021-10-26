import java.util.*;
import java.io.*;

public class p9934 {
    static int K, idx;
    static int[] tree;

    public static void travel(int cur, int[] input){
        if(cur * 2 < tree.length){
            if(tree[cur * 2] == 0){
                travel(cur * 2, input);
            }
        } else {
            tree[cur] = input[idx++];
            return;
        }

        tree[cur] = input[idx++];

        if(cur * 2 + 1 < tree.length){
            if(tree[cur * 2 + 1] == 0){
                travel(cur * 2 + 1, input);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        idx = 0;
        tree = new int[(int)Math.pow(2, K)];
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        travel(1, input);

        for(int i = 0 ; i < K ; i++){
            for(int j = (int)Math.pow(2, i) ; j < (int)Math.pow(2, i+1) ; j++){
                System.out.print(tree[j] + " ");
            }
            System.out.println();
        }
    }    
}
