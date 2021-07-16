import java.util.*;
import java.io.*;

public class p2304 {
    static Stack<int[]> stack;

    public static void insert(int[] p){
        Stack<int[]> tmp = new Stack<>();

        while(!stack.isEmpty() && p[0] > stack.peek()[0]){
            tmp.push(stack.pop());
        }

        stack.push(p);
        while(!tmp.isEmpty()){
            stack.push(tmp.pop());
        }
    }

    public static void print(){
        Stack<int[]> left = new Stack<>();
        Stack<int[]> right = new Stack<>();
        int sum = 0;

        while(!stack.isEmpty()){
            int[] p = stack.pop();
            if(!left.isEmpty()){
                if(left.peek()[1] < p[1]){
                    left.push(p);
                    right.clear();
                } else {
                    while(!right.isEmpty() && right.peek()[1] <= p[1]){
                        right.pop();
                    }
                    right.push(p);
                }
            } else {
                left.push(p);
            }
        }

        int[] largest = left.pop();
        sum += largest[1];
        int[] t;
        while(!right.isEmpty()){
            t = right.pop();
            sum += t[1] * (t[0] - (right.isEmpty() ? largest[0] : right.peek()[0]));
        }
        
        while(!left.isEmpty()){
            t = left.pop();
            sum += t[1] * (largest[0] - t[0]);
            largest = t;
        }

        System.out.println(sum);
    }

    // 고려해야할 조건
    // 1. 입력받는 기둥들은 스택에 위치(index)가 큰 순으로 쌓는다
    // 2. 가장 큰 기둥을 기준으로 왼쪽, 오른쪽에 위치한 기둥들을 스택 2개에 나눠 쌓는다
    // 3. 왼쪽 스택에는 길이가 짧은 순으로 쌓는다
    //      3-1. 스택의 top보다 짧은 기둥은 넣지 않는다
    // 4. 오른쪽 스택에는 길이가 긴 순으로 쌓는다
    //      4-1. 스택의 top보다 짧은 기둥만 넣는다
    //      4-2. 스택의 top보다 큰 기둥이 들어올 경우 top보다 작을때까지 스택의 top을 pop한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
        stack = new Stack<>();

        for(int i = 0 ; i < N ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            insert(input);
        }

        print();
        
    }    
}
