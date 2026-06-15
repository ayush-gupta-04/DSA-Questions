// time : logN
// space : H


// in BST, we can guess ki p or q node ke left me honge ya right me honge.
// case 1 : p is one side and q is other side....then root is my ans.
// case 2 : now p and q will either be on the left or the right.

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;

        // p is one side and q is other side....then root is my ans.
        if((p.val <= root.val && q.val >= root.val) || (q.val <= root.val && p.val >= root.val)) return root;

        if(p.val <= root.val && q.val <= root.val){
            return lowestCommonAncestor(root.left , p , q);
        }
        return lowestCommonAncestor(root.right , p , q);
    }
}
