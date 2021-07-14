import java.io.*;

public class p11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        String[] input;
        int bitmask = 0;
        int num = 0;
        for(int i = 0 ; i < M ; i++){
            input = br.readLine().split(" ");
            if(input.length > 1) num = Integer.parseInt(input[1]) - 1;
            switch(input[0]){
                case "add":
                    bitmask |= (1<<num);
                    break;
                case "remove":
                    bitmask &= ~(1<<num);
                    break;
                case "check":
                    sb.append(((bitmask & (1<<num)) > 0 ? 1 : 0) + "\n");
                    break;
                case "toggle":
                    if((bitmask & (1<<num)) > 0){
                        bitmask &= ~(1<<num);
                    }  else {
                        bitmask |= (1<<num);
                    }
                    break;
                case "all":
                    bitmask = (1<<20) - 1;
                    break;
                case "empty":
                    bitmask = 0;
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
