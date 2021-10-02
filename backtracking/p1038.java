import java.util.*;
import java.io.*;

public class p1038 {
    static int N, idx;
    static long[] list;


    // 고려해야할 조건
    // 1. N이 0이면 0이 출력되야 한다
    // 2. 감소하는 수 중 가장 큰 수는 9876543210으로 1022번째 수이다
    // 3. 1023번째부터는 감소하는 수가 존재할 수 없으므로 -1이 출력되야 한다
    // 4. 9876543210은 int형에서 표현할 수 없는 수이므로 long을 사용한다.
    public static void backtracking(Queue<Long> q){
        Queue<Long> temp = new LinkedList<>();
        while(!q.isEmpty()){
            long num = q.poll();
            for(int i = 0 ; i <= 9 ; i++){
                if((num % 10) <= i) break;
                temp.add(num * 10 + i);
            }
    
            list[idx++] = num;
            if(idx > N) return;
        }
        backtracking(temp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        idx = 0;
        list = new long[1000000];
        Queue<Long> q = new LinkedList<>();
        for(long i = 0 ; i <= 9 ; i++){
            q.add(i);
        }

        if(N < 1023) backtracking(q);

        System.out.println(N < 1023 ? list[N] : -1);
    }    
}
