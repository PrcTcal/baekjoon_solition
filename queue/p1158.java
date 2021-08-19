import java.util.*;
import java.io.*;

public class p1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("<");
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1 ; i <= N ; i++){
            q.add(i);
        }

        int idx = 0;
        int cnt = 1;
        while(!q.isEmpty()){
            if(cnt < K){
                cnt++;
                q.add(q.poll());
            } else {
                arr[idx++] = q.poll();
                cnt = 1;
            }
        }

        sb.append(arr[0]);
        for(int i = 1 ; i < N ; i++){
            sb.append(", ").append(arr[i]);
        }
        sb.append(">");

        System.out.println(sb.toString());
    }    
}
