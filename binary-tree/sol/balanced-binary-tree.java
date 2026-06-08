// time : N
// space : 1

// I check height to check balanced or not.
// So, I need to return height and also boolean.
// If height == -1 ... not balanced.

class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    int height(TreeNode node){
        if(node==null) return 0;

        int left = height(node.left);
        int right = height(node.right);

        if(left == -1 || right == -1) return -1;

        if(Math.abs(left-right) > 1) return -1;

        return Math.max(left,right) + 1;
    }
}
