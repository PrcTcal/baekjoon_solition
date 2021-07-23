import java.util.*;
import java.io.*;

public class p2805 {
    static int N, M;
    static int[] tree;
    public static void main(String[] args) throws IOException, NoSuchElementException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        long max = 0;
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i < N ; i++){
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(tree[i], max);
        }
        
        long start = 0;
        long end = max;
        while(start <= end){
            long sum = 0;
            long cut = (start + end) / 2;

            for (int i = 0; i < N; i++) {
                if (tree[i] > cut) {
                    sum += tree[i] - cut;
                }
            }

            if (sum >= M) {
                start = cut + 1;
            } else {
                end = cut - 1;
            }
        }
        System.out.println(end);
    }
}
