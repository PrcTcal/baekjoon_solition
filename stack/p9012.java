import java.util.*;
import java.io.*;

public class p9012 {

    public static String VPS(String input){
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < input.length() ; i++){
            char c = input.charAt(i);
            if(!stack.isEmpty()){
                if(stack.peek() == '(' && c == ')'){
                    stack.pop();
                    continue;
                }
            }
            stack.push(c);
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            sb.append(VPS(br.readLine())).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
