import java.util.*;
import java.io.*;

public class p11866 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder("<");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1 ; i <= N ; i++){
            queue.add(i);
        }

        while(!queue.isEmpty()){
            for(int i = 0 ; i < K-1 ; i++){
                queue.add(queue.poll());
            }
            sb.append(queue.poll());
            if(!queue.isEmpty()){
                sb.append(", ");
            } else {
                sb.append(">");
            }
        }

        System.out.println(sb.toString());
    }    
}
