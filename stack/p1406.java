import java.util.*;
import java.io.*;

public class p1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        String tmp = br.readLine();

        for(int i = 0 ; i < tmp.length() ; i++){
            left.push(tmp.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            String input = br.readLine();
            switch(input.charAt(0)){
                case 'L':
                    if(!left.isEmpty()) right.push(left.pop());
                    break;
                case 'D':
                    if(!right.isEmpty()) left.push(right.pop());
                    break;
                case 'B':
                    if(!left.isEmpty()) left.pop();
                    break;
                case 'P':
                    left.push(input.charAt(2));
                    break;
            }
        }
        
        // 처음에 left는 StringBuilder에 insert를 해서 시간초과가 발생함
        // insert가 StringBuilder에 있는 문자열을 1칸씩 shift하고 넣기 때문에 그런듯
        // 그냥 left에 있던걸 모두 right으로 옮기면 전체 문자열이 완성된다.
        while(!left.isEmpty()){
            right.push(left.pop());
        }

        while(!right.isEmpty()){
            sb.append(right.pop());
        }
        
        System.out.println(sb.toString());
    }
}