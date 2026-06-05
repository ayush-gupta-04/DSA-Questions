// ------------ Method 1 : recursive -------------
// time : N
//   - N : number of nodes.
// space : H
//   - H : height of the tree.

// process node -> go left -> go right
class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return list;
        pre(root);
        return list;
    }
    private void pre(TreeNode node){
        if(node == null){
            return;
        }
        list.add(node.val);
        pre(node.left);
        pre(node.right);
    }
}

// ----------- Method 2 : Iterative --------------
// time : N
//   - N : number of nodes.
// space : H
//   - H : height of the tree.

// Take stack.
// process node -> add right to stack -> add left to stack.
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Deque<TreeNode> st = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        st.offerLast(root);
        while(!st.isEmpty()){
            TreeNode node = st.pollLast();
            list.add(node.val);

            // add right then left.
            if(node.right != null) st.offerLast(node.right);
            if(node.left != null) st.offerLast(node.left);
        }
        return list;
    }
}
