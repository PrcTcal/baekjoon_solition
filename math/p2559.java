import java.util.*;
import java.io.*;

public class p2559 {

    // 고려해야할 조건
    // 1. 첫날부터 K-1번째 날까지 우선 온도 합을 구한다
    // 2. 합을 구한 날짜들 중 첫날과 마지막날의 다음날을 저장해두고 하루씩 옮겨가며 온도를 빼고 더한다
    // 처음엔 모든 날의 온도를 저장하면 메모리 초과가 뜨지 않을까 싶어서 크기가 K인 배열을 만들어서 온도의 합만
    // 저장하려 했는데 이렇게 하니 시간초과가 발생함(연산이 복잡해져서 그런듯)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0;
        int sum = 0;

        for(int i = 0 ; i < K ; i++){
            sum += temp[i];
        }

        int max = sum;
        int right = K;

        for(int i = K ; i < N ; i++){
            sum = sum - temp[left++] + temp[right++];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }    
}
