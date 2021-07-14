import java.util.*;
import java.io.*;

public class p14696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
            int[] c1 = new int[5];
            st1.nextToken();
            while(st1.hasMoreTokens()){
                c1[Integer.parseInt(st1.nextToken())]++;
            }

            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            int[] c2 = new int[5];
            st2.nextToken();
            while(st2.hasMoreTokens()){
                c2[Integer.parseInt(st2.nextToken())]++;
            }

            String winner = "";
            for(int j = 4 ; j > 0 ; j--){
                if(c1[j] > c2[j]){
                    winner = "A";
                } else if(c1[j] < c2[j]){
                    winner = "B";
                }
                if(winner.length() > 0) break;
            }
            if(winner.length() == 0) winner = "D";
            sb.append(winner + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
