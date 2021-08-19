import java.util.*;
import java.io.*;

public class p1966 {
    public static class Doc {
        int pos;
        int priority;
        public Doc(int pos, int priority){
            this.pos = pos;
            this.priority = priority;
        }
    }

    // 고려해야할 조건
    // 1. 우선순위 큐는 남아있는 문서 중 우선도가 가장 높은 것을 찾기 위해 사용한다
    // 2. 문서는 별도의 큐를 만들고, 해당 큐의 첫 번째의 문서와 우선순위 큐의 첫 번째를 비교한 뒤 같으면 빼고, 다르면 큐의 마지막으로 보낸다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < T ; i++){
            Queue<Doc> q = new LinkedList<>();
            PriorityQueue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int count = 0;
            
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                int p = Integer.parseInt(st.nextToken());
                priority.add(p);
                q.add(new Doc(j, p));
            }

            while(!q.isEmpty()){
                if(q.peek().priority == priority.peek()){
                    count++;
                    if(q.peek().pos == M){
                        sb.append(count).append("\n");
                        break;
                    }
                    priority.poll();
                    q.poll();
                } else {
                    q.add(q.poll());
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
