// time : N
// space : 1

// This is exactly the same as (Same Tree)
class Solution {
    public boolean symm(TreeNode p , TreeNode q){
        // base case.
        if(p==null && q==null) return true;
        
        // check all the cases false at root.
        if(p==null && q!=null) return false;
        if(q==null && p!=null) return false;
        if(p.val != q.val) return false;

        // if not false then check the children.
        return symm(p.left , q.right) && symm(p.right , q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return symm(root.left,root.right);
    }
}
