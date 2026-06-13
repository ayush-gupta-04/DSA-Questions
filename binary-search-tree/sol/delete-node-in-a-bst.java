// time : logN
// space : H

class Solution {
    public TreeNode deleteNode(TreeNode root, int k) {
        if(root == null){
            return null;
        }

        if(root.val == k){
            TreeNode rightTree = root.right;
            TreeNode leftTree = root.left;
            root.left = null;
            root.right = null;
            if(rightTree == null){
                return leftTree;
            }
            if(leftTree == null){
                return rightTree;
            }
            TreeNode temp = rightTree;
            while(temp.left != null){
                temp = temp.left;
            }
            temp.left = leftTree;
            return rightTree;
        }

        if(k > root.val){
            root.right = deleteNode(root.right,k);
        }else{
            root.left = deleteNode(root.left , k);
        }

        return root;
    }
}
