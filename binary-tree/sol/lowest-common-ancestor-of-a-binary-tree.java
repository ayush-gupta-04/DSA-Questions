// time : N
// space : H
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if(node==null) return null;

        // in this case .. i don't need to explore further down.
        if(node==p || node ==q) return node;

        TreeNode left = lowestCommonAncestor(node.left , p,q);
        TreeNode right = lowestCommonAncestor(node.right , p,q);

        if(left != null && right != null) return node;
        if(left != null) return left;
        if(right != null) return right;
        return null;
    }
}
