// ---------------------- Brute Force ---------------------
// time :
// - N : traversal.
// - NlogN : sorting.
// - N : assigning.

// space : 
// - N : for array.
// - H : recursion.

// - traverse.
// - sort the array.
// - do inorder traversal and assign correct vals to nodes.



// ------------------------ Optimal Appraoch -------------------
// time : N
// space : H

// swap only those which needs to be swapped only.
//                                            .
// case 1 : adjacent swapped .... 1 2 3 4 5 7 6 8 9
//                                          f m

//                                      .       .
// case 2 : non-adj swapped ....  1 2 8 4 5 6 7 3 9
//                                    f m       l

class Solution {
    TreeNode first = null;
    TreeNode mid = null;
    TreeNode last = null;
    TreeNode prev = null;

    void fun(TreeNode node){
        if(node == null){
            return;
        }

        fun(node.left);

        if(prev != null && node.val < prev.val){
            // there is a violation.
            if(first == null){
                first = prev;
                mid = node;
            }else{
                last = node;
            }
        }
        
        prev = node;
        fun(node.right);
    }
    public void recoverTree(TreeNode root) {
        TreeNode[] tri = new TreeNode[4];
        fun(root);
        if(last == null){
            //swap f and m.
            int temp = first.val;
            first.val = mid.val;
            mid.val = temp;
            
        }else{
            //swap f and l.
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }
    }
}
