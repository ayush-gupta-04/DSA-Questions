// time : N
// space : H

// every node is bounded by a range {low,high}
// node -to-> right (low for right node will be node.val);
// node -to-> left (high for left node will be node.val);

class Solution {
    public boolean fun(TreeNode node , Integer low, Integer high){
        if(node == null) return true;

        if(low != null && node.val <= low) return false;
        if(high != null && node.val >= high) return false;

        boolean left = fun(node.left , low , node.val);
        if(!left) return false;
        boolean right = fun(node.right , node.val , high);
        return right;
    }
    public boolean isValidBST(TreeNode root) {
        return fun(root, null,null);
    }
}
