import java.util.*;
import java.io.*;

public class MenuRenewal {
    static Map<String, Integer> map = new HashMap<>();
    static Map<Integer, Integer> size = new HashMap<>();

    public static String sort(String str){
        String[] arr = str.split("");
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(String s : arr){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void combination(String[] menu, String cur, int depth){
        if(size.containsKey(cur.length())) {
            cur = sort(cur);
            if (map.containsKey(cur)) map.replace(cur, map.get(cur) + 1);
            else map.put(cur, 1);
            if(size.get(cur.length()) < map.get(cur)) size.replace(cur.length(), map.get(cur));
        }
        if(depth == menu.length) return;

        for(int i = depth ; i < menu.length ; i++){
            combination(menu, cur + menu[i], i+1);
        }
    }

    public static String[] solution(String[] orders, int[] course){
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();
        for(int c : course){
            list.add(c);
            size.put(c, 0);
        }

        for(String o : orders){
            combination(o.split(""), "", 0);
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() < 2) continue;
            if(list.contains(entry.getKey().length())){
                if(entry.getValue() == size.get(entry.getKey().length())){
                    arr.add(entry.getKey());
                }
            }
        }

        return arr.stream().sorted().toArray(String[]::new);
    }
    public static void main(String[] args) throws IOException {
        String[] o = { "XYZ", "XWY", "WXA"};
        int[] c = {2,3,4};
        for(String s : solution(o, c)){
            System.out.println(s);
        }
    }    
}
