import java.util.*;
import java.io.*;

public class p10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i++){
            String[] input = br.readLine().split(" ");
            switch(input[0]){
                case "push":
                    stack.push(Integer.parseInt(input[1]));
                    break;
                case "pop":
                    sb.append(stack.size() > 0 ? stack.pop() : -1).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.size() > 0 ? 0 : 1).append("\n");
                    break;
                case "top":
                    sb.append(stack.size() > 0 ? stack.peek() : -1).append("\n");
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
