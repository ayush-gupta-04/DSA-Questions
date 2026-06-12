// ------------------- Better Approach --------------------------
// time : N*logN
// space : H (recursive stack)

// flatten the left and right first.
// then make connection .. simple.
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode leftRoot = root.left;
        TreeNode rightRoot = root.right;

        if(leftRoot==null) return;

        TreeNode leftEnd = leftRoot;
        while(leftEnd.right != null) leftEnd = leftEnd.right;

        root.left = null;
        root.right = leftRoot;
        leftEnd.right = rightRoot;
    }
}


// ----------------- Optimal Appraoch -----------------
// time : N
// space : H (recursive stack)

class Solution {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        // first right then left.
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}
