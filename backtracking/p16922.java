import java.io.*;
public class p16922 {
    static boolean[] check;
    static int[] num = {1, 5, 10, 50};
    static int N, count;

    // 고려해야할 조건
    // 1. 중복이 되는 조합의 값은 count하지 않아야 한다.
    //      1-1. 중복을 피하기 위해선 앞서 선택한 숫자가 지금 고르는 숫자보다 작거나 같아야 한다 
    public static void dfs(int n, int idx, int sum){
        if(n == N){
            if(!check[sum]) {
                check[sum] = true;
                count++;
            }
            return;
        }
        
        for(int i = idx ; i < 4 ; i++){
            dfs(n+1, i, sum + num[i]);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[50 * 20 + 1];
        count = 0;
        dfs(0, 0, 0);
        System.out.println(count);
    }
}
