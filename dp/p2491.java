import java.util.*;
import java.io.*;
public class p2491 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] up = new int[N];
        int[] down = new int[N];

        for(int i = 0 ; i < N ; i++){
            up[i] = down[i] = 1;
        }

        for(int n = 1 ; n < N ; n++){
            if (arr[n] < arr[n - 1]) {
                down[n] = down[n - 1] + 1;
            } else if (arr[n] > arr[n - 1]) {
                up[n] = up[n - 1] + 1;
            } else {
                down[n] = down[n - 1] + 1;
                up[n] = up[n - 1] + 1;
            }
        }

        int max = 1;
        for(int i = 1 ; i < N ; i++){
            max = Math.max(max, Math.max(up[i], down[i]));
        }

        System.out.println(max);
        br.close();
    }
}
