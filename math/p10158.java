import java.util.*;
import java.io.*;

public class p10158 {

    // 고려해야할 조건
    // 1. 시간 제한이 0.15초인데 t의 범위는 1 ~ 2억까지 나올 수 있기 때문에 한칸씩 움직이는 식으로 for문을 활용하면 무조건 시간초과가 난다
    // 2. x축과 y축을 따로 떼어놓고 계산하는 방식으로 해야함(간단한 수학)
    // 3. 추가로 BufferedWriter를 쓰지 않으면 시간초과가 발생
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());
       
        int tp = t;
        int tq = t;
        tp = tp > w - p ? tp - (w - p) : tp;
        tq = tq > h - q ? tq - (h - q) : tq;
        if(tp < t){
            p = w;
        } else {
            p = p + tp;
            tp = 0;
        }

        if(tq < t){
            q = h;
        } else {
            q = q + tq;
            tq = 0;
        }

        p = (tp / w) % 2 == 0 ? p - (tp % w) : (tp % w);
        q = (tq / h) % 2 == 0 ? q - (tq % h) : (tq % h);
        sb.append(p);
        sb.append(" ");
        sb.append(q);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
