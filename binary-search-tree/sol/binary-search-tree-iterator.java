// ----------------- Brute Force -------------------
// space : N

// - store the inorder traversal of the tree right away in the constructor.
// - have a global idx pointing to the current element


// ------------------- Optimal Approach --------------------------

// space : H (max H nodes are in the stack).

// we will not be storing all the elements at once.
// we will be doing this by inorder-iterative way.
// - we add left-left-left... , 
// - when we pop .. then we added node.right's left-left-left until it's null.
class BSTIterator {
    Deque<TreeNode> st;

    public BSTIterator(TreeNode root) {
        this.st = new ArrayDeque<>();
        insert(root);
    }

    private void insert(TreeNode node){
        while(node != null){
            st.offerLast(node);
            node = node.left;
        }
    }
    
    public int next() {
        TreeNode top = st.pollLast();
        insert(top.right);
        return top.val;
    }
    
    public boolean hasNext() {
        return !st.isEmpty();
    }
}
