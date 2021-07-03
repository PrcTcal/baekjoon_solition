import java.util.*;
import java.io.*;

public class p1697 {
    static int[] line = new int[100001];
    static int N, K;

    public static void bfs(int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        line[n] = 0;
        while(!queue.isEmpty() && !queue.contains(K)){
            int cur = queue.poll();
            if(line[cur - 1] == 0){
                line[cur - 1] = line[cur] + 1;
                queue.add(cur - 1);
            }
            if(line[cur + 1] == 0){
                line[cur + 1] = line[cur] + 1;
                queue.add(cur + 1);
            }
            if(line[cur * 2] == 0){
                line[cur * 2] = line[cur] + 1;
                queue.add(cur * 2);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs(N);
        System.out.println(line[K]);
    }    
}
