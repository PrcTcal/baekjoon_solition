import java.util.*;
import java.io.*;

public class p13458 {

    // 고려해야할 조건
    // 1. 최악의 경우 1,000,000개의 시험장에 각각 1,000,000명의 응시자가 있는데 B와 C가 1일 경우 감독관의 수는
    //    1,000,000 * 1,000,000이고, 이는 int의 범위를 벗어나므로 long을 사용해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] room = new int[N];

        room = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] input = br.readLine().split(" ");
        int B = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        long sum = 0;
        for(int i = 0 ; i < N ; i++){
            sum++;
            room[i] -= B;
            if(room[i] > 0){
                sum += room[i] % C > 0 ? (room[i] / C) + 1 : room[i] / C;
            }
        }

        System.out.println(sum);
    }    
}
