import java.util.*;
import java.io.*;

public class p14891 {
    static LinkedList<Integer>[] lists;
    static int K;

    public static class Order {
        int wheel;
        int dir;
        public Order(int wheel, int dir){
            this.wheel = wheel;
            this.dir = dir;
        }
    }

    public static void rotate(Order o, int target){
        // 현재 바퀴 기준 오른쪽
        if(o.wheel >= target && o.wheel + 1 < 4){
            if(lists[o.wheel].get(2) != lists[o.wheel + 1].get(6)) {
                rotate(new Order(o.wheel + 1, o.dir * -1), target);
            } else {
                rotate(new Order(o.wheel + 1, 0), target);
            }
        }

        // 현재 바퀴 기준 왼쪽
        if(o.wheel <= target && o.wheel - 1 >= 0) {
            if(lists[o.wheel].get(6) != lists[o.wheel - 1].get(2)) {
                rotate(new Order(o.wheel - 1, o.dir * -1), target);
            } else {
                rotate(new Order(o.wheel - 1, 0), target);
            }
        }

        if (o.dir > 0) {
            lists[o.wheel].addFirst(lists[o.wheel].pollLast());
        } else if(o.dir < 0) {
            lists[o.wheel].addLast(lists[o.wheel].pollFirst());
        }
    }

    public static int getScore(){
        int sum = 0;
        for(int i = 0 ; i < 4 ; i++){
            System.out.println(lists[i].getFirst());
            sum += lists[i].getFirst() == 1 ? Math.pow(2, i) : 0;
        }
        return sum;
    }

    public static void print(){
        System.out.println("-------------------");
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < lists[i].size() ; j++){
                System.out.print(lists[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lists = new LinkedList[4];
        for(int i = 0 ; i < 4 ; i++){
            lists[i] = new LinkedList<>();
        }

        for(int i = 0 ; i < 4 ; i++){
            int[] input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0 ; j < input.length ; j++){
                lists[i].add(input[j]);
            }
        }

        K = Integer.parseInt(br.readLine());
        Order[] o = new Order[K];
        for(int i = 0 ; i < K ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            o[i] = new Order(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        
        for(int i = 0 ; i < K ; i++){
            rotate(o[i], o[i].wheel);
            print();
        }
        
        System.out.println(getScore());
    }    
}
