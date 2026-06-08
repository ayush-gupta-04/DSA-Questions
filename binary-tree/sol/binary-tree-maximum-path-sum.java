// time : N
// space : 1

class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = -1_000_000_00;
        solve(root,max);
        return max[0];
    }
    public int solve(TreeNode node, int[] max){
        if(node == null) return 0;

        int left = Math.max(0,solve(node.left,max));  // left could be -ve also.
        int right = Math.max(0,solve(node.right,max)); // right could be -ve also.

        int pathSum = left + right + node.val;
        max[0] = Math.max(max[0] , pathSum);

        return Math.max(left , right) + node.val;
    }
}
