import java.io.*;

public class p2661 {
    static int N;
    static boolean term = false;

    // isGood이 수행될 때는 매번 새로운 숫자가 추가되었을 때이고, 이는 이전에 수행했던 isGood을 통과했다는 뜻이므로
    // 이전 isGood에서 했던 비교를 굳이 다시 할 필요가 없다.
    // 그렇기 때문에 새로 추가된 숫자가 포함되는 부분 수열을 비교하면 되기 때문에 부분 수열의 크기를 1에서부터 str 길이의 반까지로
    // 제한하는 한 개의 for문으로 해결할 수 있다.
    public static boolean isGood(String str){
        int len = str.length();
        for(int i = 1 ; i <= len / 2 ; i++){
            String front = str.substring(len - i - i, len - i);     // 새로 추가된 숫자에 대해서 비교할 앞 부분수열
            String back = str.substring(len - i, len);              // 새로 추가된 숫자가 포함된 뒷 부분수열
            if(front.equals(back)) return false;
        }
        return true;
    }

    // 고려해야할 조건
    // 1. 숫자를 배열에 넣어놓으면 비교가 힘드므로 String으로 받아서 substring으로 비교하는 것이 쉽다.
    // 2. 좋은 수열 중 최소값이 필요하므로 낮은 수에서부터 올라가다가 좋은 수열이 완성 될 경우 더이상 연산을 하지 않아도 된다.
    //    따라서 str의 길이가 N이 됐을 때 str를 출력하고 백트래킹을 끝내면 된다.
    public static void backtracking(String str){
        if(term) return;
        if(str.length() == N){
            System.out.println(str);
            term = true;
            return;
        }

        for(int i = 1 ; i <= 3 ; i++){
            if(isGood(str + i)) backtracking(str + i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        backtracking("");
    }    
}
