import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class p2750 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        for(int i = 0 ; i < N-1 ; i++){
            for(int j = i+1 ; j < N ; j++){
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        

        for (int val : array) {
            System.out.println(val);
        }
    }
}