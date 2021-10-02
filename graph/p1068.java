import java.util.*;
import java.io.*;

public class p1068 {
    static int N, count;
    static ArrayList<Integer>[] list;
    static int[] tree;

    public static void count(int idx){
        if(list[idx].size() == 0){
            count++;
            return;
        }

        for(int child : list[idx]){
            count(child);
        }
    }
    
    public static void delete(int idx){
        if(list[idx].size() == 0){
            tree[idx] = -2;
            return;
        }

        for(int next : list[idx]){
            delete(next);
        }
        list[idx].clear();
        tree[idx] = -2;
    }

    // 고려해야할 조건
    // 1. 삭제하는 노드가 루트 노드일때 count = 0이다
    // 2. 둘째 줄에서 첫 노드가 루트 노드가 아닐수도 있다.(루트 노드의 번호가 0이 아닐수도 있다)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = 0;
        list = new ArrayList[N];
        tree = new int[N];
        for(int i = 0 ; i < list.length ; i++){
            list[i] = new ArrayList<Integer>();
        }

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int root = -1;
        for(int i = 0 ; i < input.length ; i++){
            tree[i] = input[i];
            if(input[i] >= 0){
                list[input[i]].add(i);
            } else {
                root = i;
            }
        }

        int del = Integer.parseInt(br.readLine());
        if(del != root){
            for(int i = 0 ; i < list[tree[del]].size() ; i++){
                if(list[tree[del]].get(i) == del){
                    list[tree[del]].remove(i);
                }
            }
            delete(del);
            count(root);
        }
        System.out.println(count);
        
    }    
}
