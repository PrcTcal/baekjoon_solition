import java.util.*;
import java.io.*;

public class p1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long count = Arrays.stream(br.readLine().split(" ")).filter(x -> x.length() > 0).count();
        System.out.println(count);
    }    
}
