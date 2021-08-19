import java.util.*;
import java.io.*;

public class p1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            if(!list.contains(input)) list.add(input);
        }

        // compare는 s1이 더 커서 순서가 뒤로 갈 경우 양수를 return하고, s2가 더 커서 순서가 뒤로 갈 경우 음수를 return한다(오름차순 기준)
        Collections.sort(list, new Comparator<String>(){
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()){
                    boolean l = false;
                    for(int j = 0 ; j < s1.length() ; j++){
                        if(s1.charAt(j) != s2.charAt(j)){
                            l = s1.charAt(j) > s2.charAt(j) ? true : false;
                            break;
                        }
                    }
                    return l ? 1 : -1;
                } else {
                    return s1.length() > s2.length() ? 1 : -1;
                }
            }
        });

        for(String s : list){
            sb.append(s).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
