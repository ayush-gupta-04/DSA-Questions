// time : N
// space : 1
class Solution {
    TreeNode fun(int[] nums , int s, int e){
        if(s > e) return null;

        int m = s + (e-s)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = fun(nums , s , m-1);
        root.right = fun(nums , m + 1 , e);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return fun(nums , 0, nums.length-1);
    }
}
