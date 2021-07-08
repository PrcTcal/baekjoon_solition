import java.util.*;
import java.io.*;

public class p1918 {
    static Queue<Character> q;
    
    // 고려해야할 조건들
    // 1. '('가 들어오면 stack에 쌓는다
    // 2. ')'가 들어오면 '('를 만날때까지 stack에서 pop하여 queue에 넣는다
    // 3. 연산자가 들어올 경우
    // 3-1. 우선순위가 높은 연산자가 들어올 경우(stack에는 '+' or '-'가, 들어오는 연산자는 '*' or '/'일 때)
    //      stack에 쌓는다
    // 3-2. 우선순위가 같은 연산자가 들어올 경우(stack top과 들어오는 연산자가 모두 우선순위가 같을 때)
    //      stack에 있는 연산자들을 모두 queue에 넣은 후 stack에 연산자를 push한다
    // 4. 마지막에 stack에 남아있는 연산자가 있으면 모두 queue에 추가한다
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
