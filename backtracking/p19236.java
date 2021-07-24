import java.util.*;
import java.io.*;

public class p19236 {
    static Fish[][] space;
    static ArrayList<Fish> arr;
    static int[][] dir = {
        {0,-1}, {-1, -1}, {-1, 0},
        {-1, 1}, {0, 1}, {1, 1}, 
        {1, 0}, {1, -1} 
    };
    static Fish shark;

    public static void swap(Fish f1, Fish f2){
        int tnum = f1.num;
        int td = f1.d;
        f1.num = f2.num;
        f1.d = f2.d;
        f2.num = tnum;
        f2.d = td;
    }

    public static class Fish {
        int x;
        int y;
        int num;        // 상어는 -1
        int d;
        public Fish(int x, int y, int num, int d){
            this.x = x;
            this.y = y;
            this.num = num;
            this.d = d;
        }

        public void move(){
            // 물고기일 경우
            if(num > 0){
                for(int i = 0 ; i < 8 ; i++){
                    int nx = x + dir[(d + i) % 8][0];
                    int ny = y + dir[(d + i) % 8][1];

                    if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || space[ny][nx].num == -1) continue;
                    swap(space[y][x], space[ny][nx]);
                }
            // 상어일 경우
            } else {

            }
        }
    }

    public static void dfs(int x, int y){

    }

    public static void print(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                System.out.print(space[i][j].num + "(" + (space[i][j].d + 1) + ") ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        space = new Fish[4][4];
        arr = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < 4 ; j++){
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())-1;
                space[i][j] = new Fish(j, i, num, d);
                arr.add(space[i][j]);
            }
        }
        arr.remove(0);
        space[0][0].num = -1;
        System.out.println(arr.size());
        Collections.sort(arr, new Comparator<Fish>(){
            @Override
            public int compare(Fish f1, Fish f2){
                return f1.num - f2.num;
            }
        });
        print();
        System.out.println("--------------------------------");
        for(Fish f : arr){
            System.out.println(f.num);
        }
        for(Fish f : arr){
            space[f.y][f.x].move();
        }

        print();
    }    
}
