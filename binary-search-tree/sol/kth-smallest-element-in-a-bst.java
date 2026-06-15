// time : N
// space : H

// we did inorder traversal.
// we maintained a global counter.
class Solution {
    int cnt = 0;
    int solve(TreeNode node, int k){
        if(node == null) return -1;

        int left = solve(node.left , k);
        if(left != -1) return left;

        cnt++;
        if(cnt==k) return node.val;

        int right = solve(node.right,k);
        return right;
    }
    public int kthSmallest(TreeNode root, int k) {
        return solve(root,k);
    }
}
