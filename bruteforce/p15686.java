import java.util.*;
import java.io.*;

public class p15686 {
    static int N, M;
    static ArrayList<Dot> home;
    static ArrayList<Dot> store;

    public static class Dot{
        int x;
        int y;
        public Dot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int getChickenDistance(int s, int h){
        return Math.abs(store.get(s).x - home.get(h).x) + Math.abs(store.get(s).y - home.get(h).y);
    }

    // 고려해야할 조건
    // 1. 중복없는 조합을 이용해서 남겨둘 치킨집들을 먼저 고른 후, 해당 조합에서의 치킨 거리를 구해야 한다
    public static int combination(int depth, int n, int[] arr){
        int cd = Integer.MAX_VALUE;

        // 조합이 완성된 경우 치킨거리를 계산한다
        if(depth == M){
            int sum = 0;
            for(int i = 0 ; i < home.size() ; i++){
                int min = Integer.MAX_VALUE;
                for(int j = 0 ; j < arr.length ; j++){
                    min = Math.min(min, getChickenDistance(arr[j], i));
                }
                sum += min;
            }
            return sum;

        // 조합이 완성되지 않았으면 depth를 증가시키면서 중복이 없도록 조합을 완성시킨다
        // 치킨거리가 계산되어 리턴된 경우 이전까지 계산된 치킨거리중 최소값과 비교하여 값이 더 작은 값을 저장한다
        } else {
            for(int i = n ; i < store.size() ; i++){
                arr[depth] = i;
                cd = Math.min(cd, combination(depth+1, i+1, arr));
            }
        }
        return cd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        home = new ArrayList<>();
        store = new ArrayList<>();
        
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                int w = Integer.parseInt(st.nextToken());
                if(w == 1) home.add(new Dot(j, i));
                else if(w == 2) store.add(new Dot(j, i));
            }
        }

        System.out.println(combination(0, 0, new int[M]));

    }    
}
