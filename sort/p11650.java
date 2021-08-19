import java.util.*;
import java.io.*;

public class p11650 {
    public static class Dot{
        int x;
        int y;
        public Dot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Dot> list = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            list.add(new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list, new Comparator<Dot>(){
            public int compare(Dot d1, Dot d2){
                if(d1.x == d2.x){
                    return d1.y > d2.y ? 1 : -1;
                } else {
                    return d1.x > d2.x ? 1 : -1;
                }
            }
        });

        for(Dot d : list){
            sb.append(d.x + " " + d.y).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
