import java.util.*;
import java.io.*;

public class p13412 {
    static int T;
    public static boolean isCoprime(int a, int b){
        boolean result = true;
        if(a != 1 && b != 1){
            if(Math.max(a, b) % Math.min(a, b) == 0) result = false;
            if(result){
                for(int i = 2 ; i <= Math.min(a, b) / 2 ; i++){
                    if(a % i == 0 && b % i == 0) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    // 고려해야할 조건
    // 1. a와 b는 서로소여야 한다
    // 2. a가 1에서부터 증가할경우 나중에 b와 값의 크기가 반전되는데, 이때부터는 중복이므로 실행을 멈춰야 한다(아니면 시간초과뜸)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < T ; i++){
            int num = Integer.parseInt(br.readLine());
            Set<Integer> set = new HashSet<>();
            for(int j = 1 ; j <= num ; j++){
                if(j > num / j) break;
                if(num % j == 0 && isCoprime(j, num / j)) {
                    set.add(Math.min(j, num/j));     
                }
            }
            sb.append(set.size()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
