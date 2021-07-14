import java.util.*;
import java.io.*;
public class p13300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] f = new int[6];
        int[] m = new int[6];
        int room = 0;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int sex = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());
            if(sex == 0) f[grade-1]++;
            else m[grade-1]++;
        }

        for(int i = 0 ; i < 6 ; i++){
            room += f[i] / K + (f[i] % K > 0 ? 1 : 0);
            room += m[i] / K + (m[i] % K > 0 ? 1 : 0);
        }
        
        System.out.println(room);
    }
}
