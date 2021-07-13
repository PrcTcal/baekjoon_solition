import java.util.*;
import java.io.*;

public class p1655 {
    static PriorityQueue<Integer> max;
    static PriorityQueue<Integer> min;
    
    public static int findMid(int x){
        if(max.size() == min.size()){
            if(min.size() > 0 && min.peek() < x){
                max.add(min.poll());
                min.add(x);
            } else {
                max.add(x);
            }
        } else {
            if(max.peek() > x){
                min.add(max.poll());
                max.add(x);
            } else {
                min.add(x);
            }
        }
        return max.peek();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        max = new PriorityQueue<>(Collections.reverseOrder());
        min = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i++){
           sb.append(findMid(Integer.parseInt(br.readLine())) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
