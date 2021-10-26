import java.util.*;
import java.io.*;

public class p1138 {
    static int N;
    static LinkedList<Integer> list;
    static int[] input;

    public static boolean check(int idx){
        int cnt = 0;
        int num = list.get(idx);
        Iterator<Integer> iter = list.iterator();
        for(int i = 0 ; i < idx ; i++){
            if(iter.next() > num) cnt++;
        }
        if(cnt > input[num-1]) return false;
        return true;
    }

    public static void swap(int idx){
        int tmp = list.get(idx);
        list.set(idx, list.get(idx-1));
        list.set(idx-1, tmp);
    }

    public static void print(){
        for(int i : list){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 고려해야할 조건
    // 1. 1부터 LinkedList에 넣고 list에 들어있는 숫자들이 입력으로 주어진 조건을 만족하지 않으면 하나씩 앞 숫자랑 자리를 바꾸고 다시 조건을 검사한다.
    // 2. 매번 숫자를 list에 넣을때마다 list 내 모든 숫자에 대해 검사하며, swap은 조건을 만족할때까지 시행한다.
    // 3. 특정 위치의 숫자가 조건을 만족하면 처음 위치의 다음 숫자로 넘어가서 다시 조건을 검사한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 1 ; i <= N ; i++){
            list.addFirst(i);
            if(list.size() > 1){
                for(int j = 0 ; j < list.size() ; j++){
                    int k = j;
                    while(!check(k)){
                        swap(k--);
                    }
                }
            }
        }

        for(int n : list){
            System.out.print(n + " ");
        }
    }    
}
