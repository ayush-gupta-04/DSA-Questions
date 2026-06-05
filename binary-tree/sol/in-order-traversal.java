// ------------- Method 1 : recursive --------------
// time : N
// space : H

class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return list;
        in(root);
        return list;
    }

    private void in(TreeNode node){
        if(node == null) return;
        in(node.left);
        list.add(node.val);
        in(node.right);
    }
}
// ------------ Method 2 : Iterative --------------
// time : N
// space : H

// simulate recursion using stack.

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        TreeNode node = root;

        while(true){
            if(node != null){
                st.offerLast(node);
                node = node.left;
            }else{
                if(st.isEmpty()) break;

                node = st.pollLast();
                list.add(node.val);
                node = node.right;
            }
        }

        return list;
    }
}
