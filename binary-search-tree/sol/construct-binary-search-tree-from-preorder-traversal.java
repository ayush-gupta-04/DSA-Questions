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
