// --------------------- Brute - force ------------------------
// time : N*N
// space : H


// for every node , i am counting how many nodes will be in the left and right.
// then the recursion call.
class Solution {
    TreeNode fun(int[] pre , int s, int e){
        if(s > e) return null;

        TreeNode node = new TreeNode(pre[s]);
        int l = 0;
        int r = 0;
        for(int i = s + 1; i <= e; i++){
            if(pre[i] < pre[s]) l++;
            if(pre[i] > pre[s]) r++;
        }

        TreeNode left = fun(pre , s + 1 , s + l);
        TreeNode right = fun(pre , e - r + 1 , e);
        node.left = left;
        node.right = right;

        return node;
    }
    public TreeNode bstFromPreorder(int[] pre) {
        int n = pre.length;
        return fun(pre , 0,n-1);
    }
}



// ----------------------- Better Approach ------------------
// time : NlogN
// space : N (inorder array) + N (hash map) + H (recursive)

// - sort the preorder array .. it will become the inorder traversal of the BST.
// - we have preorder and inorder array.
// - we can easily make the BT out of it.


// ------------------------ Optimal Approach -----------------
// time : N
// space : H


// we will be assigning a upper bound to every node.
// node -to-> left ... (bound for left will be node.val)(everyting will be less than node.val on left).
// node -to-> right ... (bound for right will be bound)(everyting will be less than bound on right).
// we have taken a global increasing idx.
class Solution {
    TreeNode fun(int[] pre , int bound , int[] idx){
       if(idx[0] == pre.length || pre[idx[0]] > bound) return null;

       TreeNode node = new TreeNode(pre[idx[0]]);
       idx[0]++;
       node.left = fun(pre , node.val , idx);
       node.right = fun(pre , bound , idx);
       return node;
    }
    public TreeNode bstFromPreorder(int[] pre) {
        return fun(pre , Integer.MAX_VALUE , new int[]{0});
    }
}
