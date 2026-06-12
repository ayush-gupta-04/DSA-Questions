// time : N
// space : N

class Solution {
    public TreeNode build(int[] post , int[] in, int is, int ie , int ps, int pe, HashMap<Integer, Integer> map){
        if(is > ie || ps > pe) return null;

        TreeNode node = new TreeNode(post[pe]); // end will be the root.

        int idx = map.get(post[pe]);
        int l = (idx-1) - is + 1;  // nodes in the left.
        int r = ie - (idx+1) + 1;  // nodes in the right.

        TreeNode left = build(post,in, is , idx-1 , ps , ps + l - 1 ,map);
        TreeNode right = build(post,in , idx + 1 , ie ,pe - r , pe - 1, map);

        node.left = left;
        node.right = right;

        return node;
    }

    public TreeNode buildTree(int[] in, int[] post) {
        int n = post.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(in[i], i);
        }

        return build(post,in,0,n-1,0,n-1,map);
    }
}
