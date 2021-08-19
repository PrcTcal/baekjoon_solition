import java.util.*;
import java.io.*;

public class p1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Integer[] B = new Integer[N];
        String[] tmp = br.readLine().split(" ");
        
        for(int i = 0 ; i < N ; i++){
            B[i] = Integer.parseInt(tmp[i]);
        }

        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());

        int sum = 0;
        for(int i = 0 ; i < A.length ; i++){
            sum += A[i] * B[i];
        }

        System.out.println(sum);
    }    
}
