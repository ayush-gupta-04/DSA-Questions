// time : 3N
// space : 4N

import java.util.*;


public class Main {
    public static void preInPost(TreeNode root){
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        List<Integer> in = new ArrayList<>();

        Deque<Pair> st = new ArrayDeque<>();
        st.offerLast(new Pair(root, 1));

        while(!st.isEmpty()){
            Pair p = st.pollLast();

            // add to pre.
            // inc num and push.
            // add left to st.
            if(p.num == 1){
                pre.add(p.node.val);
                p.num++;
                st.offerLast(p);
                if(p.node.left != null) st.offerLast(new Pair(p.node.left, 1));
            }
            // add to in.
            // inc num and push.
            // add right to st.
            else if(p.num == 2){
                in.add(p.node.val);
                p.num++;
                st.offerLast(p);
                if(p.node.right != null) st.offerLast(new Pair(p.node.right, 1));
            }
            
            else{
                post.add(p.node.val);
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        preInPost(root);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
class Pair{
    TreeNode node;
    Integer num;
    Pair(TreeNode node , Integer num){
        this.node = node;
        this.num = num;
    }
}
