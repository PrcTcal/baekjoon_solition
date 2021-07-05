import java.util.*;
import java.io.*;

public class p10773 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int K = Integer.parseInt(br.readLine());
        int input;
        
        for(int i = 0 ; i < K ; i++){
            input = Integer.parseInt(br.readLine());
            if(input > 0){
                stack.push(input);
            } else {
                stack.pop();
            }
        }

        System.out.println(stack.stream().reduce(0, Integer::sum));
    }    
}
