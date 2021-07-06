import java.util.*;
import java.io.*;

public class p1918 {
    static Queue<Character> q;
    
    public static int precedence(char c){
        if(c == '(') return 0;
        if(c == '+' || c == '-') return 1;
        else return 2;
    }

    public static void postfix(char[] arr){
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] >= 40 && arr[i] <= 47){
                if(arr[i] == ')'){
                    while(stack.peek() != '('){
                        q.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    if(arr[i] == '('){
                        stack.push(arr[i]);
                    } else {
                        while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(arr[i])){
                            q.add(stack.pop()); 
                        }
                        stack.push(arr[i]);
                    }
                }
            } else {
                q.add(arr[i]);
            }
            System.out.println(q.toString());
            System.out.println(stack.toString());
            System.out.println("-----------------");
        }

        while(!stack.isEmpty()){
            q.add(stack.pop());
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        q = new LinkedList<>();
        String str = br.readLine();
        char[] c = new char[str.length()];

        str.getChars(0, str.length(), c, 0);
        postfix(c);

        while(!q.isEmpty()){
            sb.append(q.poll());
        }
        System.out.println(sb);
        
    }    
}
