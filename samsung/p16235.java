import java.util.*;
import java.io.*;

public class p16235 {
    static int N, M, K;
    static LinkedList<Tree> list;
    static Queue<Tree> deadList;
    static int[][] farm;
    static int[][] nutrition;
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static class Tree implements Comparable<Tree>{
        int x;
        int y;
        int age;
        boolean alive;
        public Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
            alive = true;
        }

        @Override
        public int compareTo(Tree t){
            return this.age - t.age;
        }
    }

    public static void spring(){
        Iterator<Tree> iter = list.iterator();
        while(iter.hasNext()){
            Tree t = iter.next();
            if(farm[t.y][t.x] >= t.age){
                farm[t.y][t.x] -= t.age;
                t.age++;
            } else {
                deadList.add(t);
                iter.remove();
            }
        }
    }

    public static void summer(){
        while(!deadList.isEmpty()){
            Tree t = deadList.poll();
            farm[t.y][t.x] += (t.age / 2);
        }
    }

    public static void fall(){
        LinkedList<Tree> baby = new LinkedList<>();
        for(Tree t : list){
            if(t.age % 5 == 0){
                for(int i = 0 ; i < 8 ; i++){
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < N) baby.add(new Tree(nx, ny, 1));
                }
            }
        }
        list.addAll(0, baby);
    }

    public static void winter(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                farm[i][j] += nutrition[i][j];
            }
        }
    }

    // 고려해야할 조건
    // 1. 문제에서 처음에 입력으로 주는 (x,y)는 내가 짠 코드 기준으로는 x가 행, y가 열이므로 Tree의 x에 y를 넣고 y에 x를 넣어야함
    // 2. 시간 초과가 빡빡한 문제로 ArrayList로 구현할 경우 삽입/삭제에 O(n)이 걸리기 때문에 O(1)인 LinkedList로 구현해야한다.
    // 3. 최초에 나이를 오름차순으로 정렬해줘야한다.
    // 4. LinkedList의 addAll은 O(1)이고, 붙여줄 위치(index)를 정해줄 수 있다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        farm = new int[N][N];
        nutrition = new int[N][N];
        list = new LinkedList<>();
        deadList = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                farm[i][j] = 5;
            }
        }

        for(int i = 0 ; i < N ; i++){
            nutrition[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0 ; i < M ; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(new Tree(input[1]-1, input[0]-1, input[2]));
        }

        Collections.sort(list);
        for(int i = 0 ; i < K ; i++){
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(list.size());
    }    
}
