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

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Traverse down the left spine, pushing nodes to defer their visit
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Node has no unvisited left children; process it
            curr = stack.pop();
            result.add(curr.val);

            // Shift focus to the right subtree
            curr = curr.right;
        }

        return result;
    }
}



// ---------------- Method 3 : Morris ------------


// time : N amortised.
// space : 1

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        
        TreeNode curr = root;

        while(curr != null){
            if(curr.left == null){
                // no left.
                list.add(curr.val);
                curr = curr.right;
            }else{
                // left exist.
                // find thread.
                // if thread exist .. means we have visited left .. remove thread, print node, move to right.
                // else means we have not explored left .. make thread, move left.

                TreeNode last = curr.left;
                while(last.right != null && last.right != curr){
                    last = last.right;
                }

                if(last.right == null){
                    // no thread.
                    last.right = curr;
                    curr = curr.left;
                }else{
                    // thread exist already.
                    last.right = null;
                    list.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return list;
    }
}
