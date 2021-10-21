import java.util.*;
import java.io.*;

public class p14890 {
    static int N, L;
    static int[][] map;
    static int way;

    public static void colMatch(int idx){
        int down = 0;
        int up = 1;
        boolean check = true;
        for(int i = 0 ; i < N-1 ; i++){
            if (down > 0) {
                if (map[i][idx] == map[i + 1][idx]) {
                    down--;
                } else {
                    check = false;
                    break;
                }
            } else {
                if (map[i][idx] == map[i + 1][idx]) {
                    // up 쌓기
                    up++;
                } else if (map[i][idx] + 1 == map[i + 1][idx]) {
                    // up 사용하기
                    if (up >= L) {
                        up = 1;
                    } else {
                        check = false;
                        break;
                    }
                } else if(map[i][idx] == map[i + 1][idx] + 1){
                    // down 사용하기
                    down = L - 1;
                    up = 0;
                } else {
                    check = false;
                    break;
                }
            }
        }
        if (check && down == 0) way++;
    }

    public static void rowMatch(int idx){
        int down = 0;
        int up = 1;
        boolean check = true;
        for(int i = 0 ; i < N-1 ; i++){
            if(down > 0){
                if(map[idx][i] == map[idx][i+1]) {
                    down--;
                } else {
                    check = false;
                    break;
                }
            } else {
                if(map[idx][i] == map[idx][i+1]){
                    // up 쌓기
                    up++;
                } else if(map[idx][i] + 1 == map[idx][i+1]){
                    // up 사용하기
                    if(up >= L) {
                        up = 1;
                    } else {
                        check = false;
                        break;
                    }
                } else if(map[idx][i] == map[idx][i+1] + 1){
                    // down 사용하기
                    down = L-1;
                    up = 0;
                } else {
                    check = false;
                    break;
                }
            }
        }
        if(check && down == 0) way++;
    }

    // 고려해야할 조건
    // 1. 처음엔 큐를 사용해서 큐에는 연속된 수를 넣고, 그 앞뒤의 수를 비교해가며 구현하려 했으나 너무 복잡해서 실패함
    // 2. 내리막을 다 만들기까지 남은 카운트인 down과 오르막을 만들 수 있는지 여부를 판별하기 위해 연속된 같은 블럭의 수를 저장한 up을 사용
    // 3. down > 0이면 내리막을 만들고 있는 중이므로 i와 i+1번째 블럭의 높이가 다르면 지나갈 수 없는 경로임
    // 4. down이 0일때는 i+1번째 블럭의 높이가 같을때, 클때, 작을때로 나누어 구현한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        way = 0;

        for(int i = 0 ; i < N ; i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0 ; i < N ; i++){
            colMatch(i);
            rowMatch(i);
        }
        System.out.println(way);
    }    
}
