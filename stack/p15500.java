import java.util.*;
import java.io.*;

public class p15500 {
    static Stack<Integer>[] a;
    static int count;
    static StringBuilder sb;

    public static void Hanoi(int n){
        while(!a[0].isEmpty() || !a[1].isEmpty()){
            if(a[1].isEmpty()){
                while(!a[0].isEmpty()){
                    count++;
                    if(a[0].peek() == n){
                        a[2].push(a[0].pop());
                        n--;
                        sb.append("1 3\n");
                    } else {
                        a[1].push(a[0].pop());
                        sb.append("1 2\n");
                    }
                }
            } else if(a[0].isEmpty()){
                while(!a[1].isEmpty()){
                    count++;
                    if(a[1].peek() == n){
                        a[2].push(a[1].pop());
                        n--;
                        sb.append("2 3\n");
                    } else {
                        a[0].push(a[1].pop());
                        sb.append("2 1\n");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        a = new Stack[3];
        a[0] = new Stack<>();
        a[1] = new Stack<>();
        a[2] = new Stack<>();
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(x -> a[0].push(x));
        Hanoi(N);
        System.out.println(count);
        System.out.println(sb);
    }    
}
