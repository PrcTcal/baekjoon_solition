import java.io.*;
public class p5639 {
    static StringBuilder sb;
    public static class Node{
        Node left;
        Node right;
        int val;
        public Node(int v){
            this.val = v;
        }
    }

    public static Node insert(Node node, int x){
        if(node == null){
            return new Node(x);
        } else {
            if(x > node.val) node.right = insert(node.right, x);
            else node.left = insert(node.left, x);
            return node;
        }
    }

    public static void postorder(Node node){
        if(node.left != null) postorder(node.left);
        if(node.right != null) postorder(node.right);
        sb.append(node.val + "\n");
    }

    // 처음엔 크기가 10001인 int 배열을 사용하여 문제를 풀려고 하였으나
    // 완전 이진 트리가 아닐 가능성이 있기 때문에 배열을 사용할 경우 indexOutOfBoundException이 발생한다
    // 따라서 Node 정적 클래스를 이용하여 풀어야 한다.
    // 추가적으로 입력 받을때 IDE에서는 반복문 조건을 !equals("")로 했으나 백준에 제출시는 null값 비교로 해야 
    // NullPointerException을 피할 수 있다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;
        sb = new StringBuilder();
        Node root = new Node(Integer.parseInt(br.readLine()));

        while((input = br.readLine()) != null){
            insert(root, Integer.parseInt(input));
        }

        postorder(root);
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
