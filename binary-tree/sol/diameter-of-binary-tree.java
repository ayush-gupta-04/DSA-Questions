// time : N
// space : 1

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        solve(root,max);
        return max[0];
    }

    public int solve(TreeNode node, int[] max){
        if(node == null) return -1;

        int left = solve(node.left,max);
        int right = solve(node.right , max);

        // calc the max path length.
        int path = left + right + 2;
        max[0] = Math.max(max[0] , path);

        // returns the max of left or right.
        return Math.max(left , right) + 1;
    }
}
