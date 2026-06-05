// ------------------ Method 1 : recursive -------------------
// time : N
// space : H

class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return list;
        post(root);
        return list;
    }
    private void post(TreeNode node){
        if(node == null) return;
        
        post(node.left);
        post(node.right);
        list.add(node.val);
    }
}


// ----------------------- Method 2 : Iterative Using 2 Stack ---------------------------
// time : N
// space : 2*H

// slightly same as pre-order.
// add node to st2 -> add left to st1 -> add right to st1

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        Deque<TreeNode> st1 = new ArrayDeque<>();
        Deque<Integer> st2 = new ArrayDeque<>();

        st1.offerLast(root);
        while(!st1.isEmpty()){
            TreeNode node = st1.pollLast();
            st2.offerLast(node.val);

            if(node.left != null) st1.offerLast(node.left);
            if(node.right != null) st1.offerLast(node.right);
        }

        while(!st2.isEmpty()){
            list.add(st2.pollLast());
        }

        return list;
    }
}



// ----------------------- Method 2 : Iterative Using 1 Stack ---------------------------
// time : N
// space : H

// add node to list -> add left to st1 -> add right to st1
// reverse list
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        Deque<TreeNode> st1 = new ArrayDeque<>();

        st1.offerLast(root);
        while(!st1.isEmpty()){
            TreeNode node = st1.pollLast();
            list.add(node.val);

            if(node.left != null) st1.offerLast(node.left);
            if(node.right != null) st1.offerLast(node.right);
        }

        Collections.reverse(list);
        return list;
    }
}
