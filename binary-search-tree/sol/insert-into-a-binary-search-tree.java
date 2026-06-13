// ------------------- Recursive Solution -----------------

// time : logN
// space : H

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }
        if(val > root.val){
            root.right = insertIntoBST(root.right,val);
        }else{
            root.left = insertIntoBST(root.left , val);
        }
        return root;
    }
}


// ------------------- Iterative Solution -----------------

// time : logN
// space : 1

// since we have conditional movement here , we can easily do it with loops.
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        TreeNode curr = root;

        while(curr != null){
            if(val > curr.val){
                // move to right.
                if(curr.right == null){
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }else{
                if(curr.left == null){
                    curr.left = new TreeNode(val);
                    break;
                }
                curr = curr.left;
            }
        }

        return root;
    }
}
