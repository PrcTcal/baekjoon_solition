import java.util.*;
import java.io.*;

public class p15953 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] con1 = {5000000, 3000000, 2000000, 500000, 300000, 100000};
        int[] con2 = {5120000, 2560000, 1280000, 640000, 320000};

        for(int i = 0 ; i < T ; i++){
            String[] input = br.readLine().split(" ");
            int first = Integer.parseInt(input[0]);
            int second = Integer.parseInt(input[1]);
            int prize = 0;

            if(first > 0){
                for(int j = 1 ; j <= 6 ; j++){
                    if(first > j){
                        first -= j;
                    } else {
                        prize += con1[j-1];
                        break;
                    }
                }
            }

            if(second > 0){
                for(int j = 1 ; j <= 5 ; j++){
                    int p = (int)Math.pow(2, j-1);
                    if(second > p){
                        second -= p;
                    } else {
                        prize += con2[j-1];
                        break;
                    }
                }
            }
            System.out.println(prize);
        }
    }
}
