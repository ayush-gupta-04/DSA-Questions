// time : N
// space : H 

// I must check the path sum only on the leaf.
class Solution {
    boolean solve(TreeNode node , int sum , int target){
        if(node==null) return false;
        if(node.left==null && node.right==null){
            sum += node.val;
            return sum == target;
        }

        boolean left = solve(node.left , sum + node.val , target);
        if(left) return true;
        boolean right = solve(node.right , sum + node.val , target);
        return right;
    }
    public boolean hasPathSum(TreeNode root, int target) {
        if(root==null){
            return false;
        }
        return solve(root,0,target);
    }
}
