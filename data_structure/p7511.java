import java.io.*;

public class p7511 {
    static int[] people;
    static int[] cnt;

    public static int getRoot(int x){
        if(people[x] == x) return x;
        return getRoot(people[x]);
    }

    public static int getParent(int x){
        if(people[x] == 0){
            people[x] = x;
            return x;
        }
        if(people[x] == x) return x;
        int p = getParent(people[x]);
        people[x] = p;
        return p;
    }

    public static void unionParent(int a, int b){
        int x = getParent(a);
        int y = getParent(b);
        if(x != y){
            if(cnt[x] >= cnt[y]){
                people[y] = x;
                cnt[x] += cnt[y];
            } else {
                people[x] = y;
                cnt[y] += cnt[x];
            }
        }
    }

    // 미해결 문제 - 시간 초과
    // 분리 집합을 이용하여 풀이하려 했으나 계속 시간초과가 발생하여 일단 스킵함
    // 시간 초과 원인을 아직 파악하지 못함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n, k, m;
        String[] tmp;

        for(int i = 0 ; i < T ; i++){
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            people = new int[n+1];
            cnt = new int[n+1];
            for(int j = 0 ; j < cnt.length ; j++){
                cnt[j] = 1;
            }
            
            for(int j = 0 ; j < k ; j++){
                tmp = br.readLine().split(" ");
                unionParent(Integer.parseInt(tmp[0]) + 1, Integer.parseInt(tmp[1]) + 1);
            }

            m = Integer.parseInt(br.readLine());
            System.out.println("Scenario " + (i + 1) + ":");

            for(int j = 0 ; j < m ; j++){
                tmp = br.readLine().split(" ");
                System.out.println(getRoot(Integer.parseInt(tmp[0]) + 1) == getRoot(Integer.parseInt(tmp[1]) + 1) ? 1 : 0);
            }
        }
    }    
}
