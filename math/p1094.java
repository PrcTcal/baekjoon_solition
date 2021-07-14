import java.util.*;
import java.io.*;

public class p1094 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int sum = 64;
        Stack<Integer> sticks = new Stack<>();
        sticks.push(64);

        while(sum != X){
            int top = sticks.pop();
            sum -= top;
            int tmp = top / 2;

            if(sticks.isEmpty()){
                sum = tmp;
            } else {
                sum += tmp;
            }

            sticks.push(tmp);
            
            if(sum < X) {
                sticks.push(tmp);
                sum += tmp;
            }
        }
        System.out.println(sticks.size());
    }
}
