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

        // 시계방향 회전
        if (o.dir > 0) {
            lists[o.wheel].addFirst(lists[o.wheel].pollLast());

        // 반시계방향 회전
        } else if(o.dir < 0) {
            lists[o.wheel].addLast(lists[o.wheel].pollFirst());
        }
    }

    // 점수 계산
    public static int getScore(){
        int sum = 0;
        for(int i = 0 ; i < 4 ; i++){
            sum += lists[i].getFirst() == 1 ? Math.pow(2, i) : 0;
        }
        return sum;
    }

    // 고려해야할 조건
    // 1. 톱니 회전 특성상 양방향 큐가 필요하되, 3번째와 7번째에 있는 숫자를 확인할 수 있어야 해서 LinkedList를 사용했다.
    // 2. 회전하기 전 상태에서 톱니간 비교를 통해 어느 방향으로 회전할지 미리 정하기 위해 재귀를 통해 타겟 톱니에서부터 주변으로
    //    rotate 함수를 호출하도록 했다.
    // 3. 각 톱니에게 회전할 방향을 정해준 후에는 방향에 따라 회전시킨다.
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
        }
        
        System.out.println(getScore());
    }    
}
