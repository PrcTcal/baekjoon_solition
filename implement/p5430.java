import java.util.*;
import java.io.*;

public class p5430 {

    // 고려해야할 조건
    // 1. LinkedList를 사용시 삽입/삭제에 O(1)이 걸리는 것을 활용하여 StringBuilder에 추가시 removeFirst()/removeLast()를 사용해야 시간초과가 발생하지 않음
    // 2. ArrayList를 사용시 삽입/삭제에 O(n)이 걸리므로 head와 tail 변수를 만들어 명령어를 수행하면서 index만 추적해준 뒤, head와 tail 사이에 있는 숫자만 검색하여
    //    StringBuilder에 추가해야 시간초과가 발생하지 않는다
    // 3. reverse는 R이 10만개 있을 경우 매번 역순으로 뒤집을 시 O(N^2)가 되어 시간초과가 발생한다.
    //    그러므로 직접 뒤집기보단 초기 상태에서 R일 경우 List의 뒤에서부터 제거하도록 하고, 다시 R이 나올 경우 앞에서부터 제거하도록 한다
    //    추가적으로 처음 명령을 입력받을 때 RR처럼 연속해서 있을 경우 제거해주면 시간을 좀 더 줄일 수 있다.
    // 4. ArrayList로 구현시 항상 head <= tail이라는 조건을 잘 걸어둬야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // LinkedList를 사용하는 코드
        for (int i = 0; i < T; i++) {
            String orders = br.readLine().replaceAll("R{2}", "");
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            LinkedList<Integer> list = new LinkedList<>();

            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            boolean flag = true;
            boolean reverse = false;
            for (int j = 0; j < orders.length() && flag; j++) {
                switch (orders.charAt(j)) {
                case 'R':
                    reverse = !reverse;
                    break;
                case 'D':
                    if (list.size() > 0) {
                        if (!reverse) list.removeFirst();
                        else list.removeLast();
                    } else {
                        sb.append("error\n");
                        flag = false;
                    }
                    break;
                }
            }

            if (flag) {
                sb.append("[");
                if (list.size() > 0) {
                    if (!reverse) {
                        while (list.size() > 0) {
                            sb.append(list.removeFirst()).append(",");
                        }
                    } else {
                        while (list.size() > 0) {
                            sb.append(list.removeLast()).append(",");
                        }
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append("]\n");
            }
        }

        // ArrayList를 사용하는 코드
        // for(int i = 0 ; i < T ; i++){
        //     String orders = br.readLine().replaceAll("R{2}", "");
        //     int n = Integer.parseInt(br.readLine());
        //     StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
        //     ArrayList<Integer> list = new ArrayList<>();
            
        //     while(st.hasMoreTokens()){
        //         list.add(Integer.parseInt(st.nextToken()));
        //     }
            
        //     boolean flag = true;
        //     boolean reverse = false;
        //     int head = 0;
        //     int tail = list.size()-1;
        //     for(int j = 0 ; j < orders.length() && flag ; j++){
        //         switch(orders.charAt(j)){
        //             case 'R':
        //                 reverse = !reverse;
        //                 break;
        //             case 'D':
        //                 if(list.size() > 0 && head <= tail) {
        //                     if(!reverse) head++;
        //                     else tail--;
        //                 } else {
        //                     sb.append("error\n");
        //                     flag = false;
        //                 } 
        //                 break;
        //         }
        //     }

        //     if(flag){
        //         sb.append("[");
        //         if(list.size() > 0 && head <= tail) {
        //             if(!reverse){
        //                 while(head<=tail){
        //                     sb.append(list.get(head++)).append(",");
        //                 }
        //             } else {
        //                 while(head<=tail){
        //                     sb.append(list.get(tail--)).append(",");
        //                 }
        //             }
        //             sb.deleteCharAt(sb.length()-1);
        //         }
        //         sb.append("]\n");
        //     }
        // }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }    
}
