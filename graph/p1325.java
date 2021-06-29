import java.util.*;
import java.io.*;

public class p1325 {
    static ArrayList<Integer>[] list = new ArrayList[10001];
    static int[] count;
    static boolean[] visit;

    public static void bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        visit[idx] = true;
        while(!queue.isEmpty()){
            int pos = queue.poll();
            for(Integer p : list[pos]){
                if(!visit[p]){
                    queue.add(p);
                    visit[p] = true;
                    count[p]++;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N, M, A, B;
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N+1];
        
        for(int i = 0 ; i <= N ; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            list[A].add(B);
        }

        for(int i = 1 ; i <= N ; i++){
            visit = new boolean[N + 1];
            bfs(i);
        }

        for(int i = 1 ; i <= N ; i++){
            max = Math.max(max, count[i]);
        }

        for(int i = 1 ; i <= N ; i++){
            if(count[i] == max){
                sb.append(i + " ");
            }
        }
    
        System.out.println(sb.toString());
    }
}
