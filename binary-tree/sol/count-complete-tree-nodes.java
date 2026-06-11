// time : NlogN
// space : H or N

// if left height and right height are equal .. we are 100% sure that we can find the height of the node in O(1).
// number of nodes = 2^l - 1.
class Solution {
    int leftHeight(TreeNode node){
        int cnt = 0;
        TreeNode temp = node;
        while(temp != null){
            temp = temp.left;
            cnt++;
        }
        return cnt;
    }
    int rightHeight(TreeNode node){
        int cnt = 0;
        TreeNode temp = node;
        while(temp != null){
            temp = temp.right;
            cnt++;
        }
        return cnt;
    }
    public int countNodes(TreeNode root) {
        if(root==null) return 0;

        int l = leftHeight(root);
        int r = rightHeight(root);

        if(l==r){
            return (1<<l)-1;
        }

        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }
}
