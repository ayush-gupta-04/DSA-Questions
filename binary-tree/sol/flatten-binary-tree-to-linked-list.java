// ------------------- Better Approach --------------------------
// time : N*logN
// space : H (recursive stack)

// flatten the left and right first.
// then make connection .. simple.
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode leftRoot = root.left;
        TreeNode rightRoot = root.right;

        if(leftRoot==null) return;

        TreeNode leftEnd = leftRoot;
        while(leftEnd.right != null) leftEnd = leftEnd.right;

        root.left = null;
        root.right = leftRoot;
        leftEnd.right = rightRoot;
    }
}


// ----------------- Optimal Appraoch -----------------
// time : N
// space : H (recursive stack)

class Solution {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        // first right then left.
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}



// -------------------- Approach : Using Morris Traversal ---------------------

// time : N
// space : 1

//         1
//       /   \
//      2     5
//     / \     \
//    3   4     6

// Using the morris traversal.
// if i am at 1,
// - i will connect leftSubtree's 4 to the 5.
// - then 1's right will become leftSubtree.
// - will move to right.

class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){
                TreeNode prev = curr.left;
                while(prev.right != null){
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
