import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p2751 {

    public static int[] arr;
    public static int[] tmp;

    public static void mergeSort(int st, int ed){
        if(st < ed){
            int mid = (st + ed) / 2;
            mergeSort(st, mid);
            mergeSort(mid+1, ed);

            int p = st;
            int q = mid + 1;
            int idx = p;

            while(p <= mid || q <= ed){
                if(q > ed || (p <= mid && arr[p] < arr[q])){
                    tmp[idx++] = arr[p++];
                } else {
                    tmp[idx++] = arr[q++];
                }
            }

            for(int i = st ; i <= ed ; i++){
                arr[i] = tmp[i];
            }
            print();
        }
    }

    public static void print(){
        for(int i = 0 ; i < arr.length ; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        /*
        *   merge sort를 구현하여 사용하는 방식.
        *   2751번 문제에서는 시간초과가 떴음. 따라서 Collections.sort()를 이용하여 풀이
        *   list값을 일일이 출력하는 것도 시간초과에 영향을 미침. 따라서 StringBuilder로 묶어서 출력
        *
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tmp = new int[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        print();
        mergeSort(0, N-1);
        *
        *
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        for(int val : list){
            sb.append(val).append('\n');
        }
        System.out.println(sb);
    }
}