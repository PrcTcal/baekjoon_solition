import java.util.*;
import java.io.*;

public class p15787 {
    static int N, M;
    static int[] bitmask;

    // 고려해야할 조건
    // 1. 3번 명령어의 경우 20번째 자리에 사람이 있을 때 left shift하면 21자리가 되므로 shift 후에 21번째 자리를 없애줘야 한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bitmask = new int[N];
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int code = Integer.parseInt(st.nextToken());
            int train = Integer.parseInt(st.nextToken());
            int seat;
            switch(code){
                case 1:
                    seat = Integer.parseInt(st.nextToken());
                    bitmask[train-1] |= (1 << (seat-1));
                    break;
                case 2:
                    seat = Integer.parseInt(st.nextToken());
                    bitmask[train-1] &= ~(1 << (seat-1));
                    break;
                case 3:
                    bitmask[train-1] <<= 1;
                    bitmask[train-1] &= ((1 << 20) - 1);
                    break;
                case 4:
                    bitmask[train-1] >>>= 1;
                    break;
            }
        }

        for(int i = 0 ; i < N ; i++){
            if(!list.contains(bitmask[i])) {
                list.add(bitmask[i]);
            }
        }

        System.out.println(list.size());
    }    
}
