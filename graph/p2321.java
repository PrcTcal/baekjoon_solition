import java.util.*;
import java.io.*;

public class p2321 {

    public static int calc(int n, int p){
        int num = n;
        int result = 0;
        while(num > 0){
            result += Math.pow(num % 10, p);
            num /= 10;
        }
        return result;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        int P = Integer.parseInt(input[1]);

        arr.add(Integer.parseInt(input[0]));
        int num = calc(arr.get(0), P);

        while(!arr.contains(num)){
            arr.add(num);
            num = calc(arr.get(arr.size() - 1), P);
        }

        System.out.println(arr.indexOf(num));
    }
}