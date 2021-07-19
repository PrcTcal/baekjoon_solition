import java.util.*;
import java.io.*;
public class p2628 {

    // 고려해야할 조건
    // 1. 자르고 난 후의 가로값들과 세로값들을 이중 for문으로 돌려가면서 면적을 계산하면서 최대값을 저장한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(br.readLine());
        ArrayList<Integer> col = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();

        for(int i = 0 ; i < T ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            if(Integer.parseInt(st.nextToken()) == 0){
                col.add(Integer.parseInt(st.nextToken()));
            } else {
                row.add(Integer.parseInt(st.nextToken()));
            }
        }

        col.add(n);
        row.add(m);
        Collections.sort(col);
        Collections.sort(row);
        int max = 0;
        int cs = 0, rs = 0;

        for(int c : col){
            for(int r : row){
                if(max < (c-cs) * (r-rs)){
                    max = (c-cs) * (r-rs);
                }
                rs = r;
            }
            rs = 0;
            cs = c;
        }
        System.out.println(max);
    }
}
