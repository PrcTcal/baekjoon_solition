import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class p10989{
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(buf.readLine());
        int biggest = 0;
        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(buf.readLine());
            if(arr[i] > biggest) biggest = arr[i];
        }
        buf.close();

        int[] arr2 = new int[biggest+1];
        for(int i = 0 ; i < N ; i++){
            arr2[arr[i]]++;
        }
        for(int i = 1 ; i <= biggest ; i++){
            arr2[i] += arr2[i-1];
        }

        int[] result = new int[N];
        for(int i = 0 ; i < N ; i++){
            result[--arr2[arr[i]]] = arr[i];
        }

        for(int i : result){
            sb.append(i).append('\n');
        }
        System.out.print(sb.toString());
    }
}