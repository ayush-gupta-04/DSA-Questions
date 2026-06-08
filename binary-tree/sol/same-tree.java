// time : N
// space : 1

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // base case.
        if(p==null && q==null) return true;
        
        // check all the cases false at root.
        if(p==null && q!=null) return false;
        if(q==null && p!=null) return false;
        if(p.val != q.val) return false;

        // if not false then check the children.
        return isSameTree(p.left , q.left) && isSameTree(p.right , q.right);
    }
}
