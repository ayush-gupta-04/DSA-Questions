// time : N
// space : N

class Solution {
    public TreeNode build(int[] pre , int[] in, int is, int ie , int ps, int pe, HashMap<Integer, Integer> map){
        if(is > ie || ps > pe) return null;

        TreeNode node = new TreeNode(pre[ps]);

        int idx = map.get(pre[ps]);
        int l = (idx-1) - is + 1;  // nodes in the left.
        int r = ie - (idx+1) + 1;  // nodes in the right.

        TreeNode left = build(pre,in, is , idx-1 , ps + 1, ps + l ,map);
        TreeNode right = build(pre,in , idx + 1 , ie , pe - r + 1, pe, map );

        node.left = left;
        node.right = right;

        return node;
    }
    public TreeNode buildTree(int[] pre, int[] in) {
        int n = pre.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(in[i], i);
        }

        return build(pre,in,0,n-1,0,n-1,map);
    }
}
