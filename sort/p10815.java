import java.util.*;
import java.io.*;

public class p10815 {
    static int[] cards;
    static StringBuilder sb;

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int partition(int[] arr, int left, int right){
        int mid = (left + right) / 2;
        swap(arr, left, mid);

        int pivot = arr[left];
        int i = left, j = right;
        while(i < j){
            while(pivot < arr[j]){
                j--;
            }

            while(i < j && pivot >= arr[i]){
                i++;
            }
            swap(arr, i, j);
        }
        arr[left] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void quickSort(int[] arr, int left, int right){
        if(left >= right) return;

        int pivot = partition(arr, left, right);

        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    public static void binarySearch(int start, int end, int target){
        if(start == end){
            if(cards[start] != target){
                sb.append(0).append(" ");
            } else {
                sb.append(1).append(" ");
            }
            return;
        } else if(start > end){
            sb.append(0).append(" ");
            return;
        }

        int mid = (start + end) / 2;
        if(cards[mid] > target){
            binarySearch(start, mid-1, target);
        } else if(cards[mid] < target){
            binarySearch(mid+1, end, target);
        } else {
            sb.append(1).append(" ");
            return;
        }
    }

    // 고려해야할 조건
    // 1. 배열에서 데이터를 찾을 때 전체를 검색하는게 아니라 탐색 범위를 반씩 줄여가며 찾는 이분 탐색 알고리즘을 사용해야 한다
    // 2. 이분 탐색 알고리즘을 적용시키기 위해선 배열이 정렬되어 있어야 하는데 카드에 적혀있는 수의 범위가 크므로 카운팅 정렬은 사용할 수 없다
    // 3. 따라서 피벗을 배열 중간에 위치한 값으로 가지는 퀵소트로 정렬한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N ; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(cards, 0, N-1);
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < M ; i++){
            binarySearch(0, N-1, Integer.parseInt(st.nextToken()));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
