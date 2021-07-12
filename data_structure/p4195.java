import java.util.*;
import java.io.*;

public class p4195 {
    static int[] parent;    // 입력받는 매 관계마다 새로운 사람 2명이 추가될 경우 최대 2 * K만큼의 사람이 들어갈 수 있음
    static int[] level;

    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static int union(int x, int y){
        x = find(x);
        y = find(y);
        
        // 합집합시 y에 연결되있던 수(층수)를 x의 층수에 더해주어야 한다
        if(x != y){
            parent[y] = x;
            level[x] += level[y];
        }
        return level[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T ; i++){
            int K = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            int idx = 0;
            parent = new int[2 * K];
            level = new int[2 * K];

            for(int j = 0 ; j < 2 * K ; j++){
                parent[j] = j;
                level[j] = 1;
            }

            for(int j = 0 ; j < K ; j++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();

                if(!map.containsKey(a)) map.put(a, idx++);
                if(!map.containsKey(b)) map.put(b, idx++);

                sb.append(union(map.get(a), map.get(b)) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
