import java.util.*;
import java.io.*;

public class p16964 {
    static boolean[] visit;
    static HashMap<Integer, ArrayList<Integer>> map;
    static Stack<Integer> stack;
    static boolean result;

    public static void specialJudge(int x){
        if(visit[x-1]) return;
        visit[x-1] = true;

        if(map.containsKey(x)){
            while(!map.get(x).isEmpty() && result){
                if(map.get(x).contains(stack.peek())){
                    map.get(x).remove(stack.peek());
                    specialJudge(stack.pop());
                } else {
                    result = false;
                }
            }
        }
    }

    // 실패(나중에 다시 풀기)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visit = new boolean[N];
        stack = new Stack<>();
        map = new HashMap<>();
        result = true;

        for(int i = 0 ; i < N-1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(map.containsKey(s)){
                map.get(s).add(e);
            } else {
                map.put(s, new ArrayList<Integer>(Arrays.asList(e)));
            }
        }

        String[] input = br.readLine().split(" ");
        for(int i = input.length-1 ; i >= 0 ; i--){
            stack.push(Integer.parseInt(input[i]));
        }

        if(stack.peek() == 1){
            specialJudge(stack.pop());
        } else {
            result = false;
        }
        System.out.println(result ? 1 : 0);
    }    
}
