import java.io.*;

public class p10989{

    public static void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
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

            while(pivot >= arr[i] && i < j){
                i++;
            }
            swap(arr, i, j);
        }

        arr[left] = arr[i];
        arr[i] = pivot;
        return i;
    }

    // 퀵소트로 하면 시간초과 발생함
    // 입력 개수가 최대 1억이라 시간이 빡빡한 문제다.
    public static void quickSort(int[] arr, int left, int right){
        if(left >= right) return;

        int pivot = partition(arr, left, right);

        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] count = new int[10000];

        for(int i = 0 ; i < N ; i++){
            count[Integer.parseInt(br.readLine())]++;
        }

        for(int i = 1 ; i < 10000 ; i++){
            while(count[i] > 0){
                sb.append(i).append("\n");
                count[i]--;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}