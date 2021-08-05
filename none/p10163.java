import java.util.*;
import java.io.*;

public class p10163 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] space = new int[1001][1001];
        int[] paper = new int[N+1];

        
        for(int i = 1 ; i <= N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            paper[i] = w * h;
            for(int j = 0 ; j < h ; j++){
                for(int k = 0 ; k < w ; k++){
                    if(space[y+j][x+k] > 0) paper[space[y+j][x+k]]--;
                    space[y+j][x+k] = i;
                }
            }
        }

        for(int i = 1 ; i <= N ; i++){
            sb.append(paper[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
