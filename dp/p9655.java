import java.io.*;

public class p9655 {
    static int N;

    // 유형은 dp이지만 사실상 dp로 푸는게 의미가 없는 문제이므로 그냥 풂
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        System.out.println(N % 2 == 1 ? "SK" : "CY");
    }    
}
