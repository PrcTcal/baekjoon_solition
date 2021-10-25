import java.io.*;

public class p1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = 10;
        int idx = 1;
        long answer = 0;

        while(N / k > 0){
            answer += idx * (k/10) * 9;
            idx++;
            k *= 10;
        }
        answer += idx * (N - (k/10) + 1);

        System.out.println(answer);
    }    
}
