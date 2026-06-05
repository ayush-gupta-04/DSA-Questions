// time : N
// space : N (In the worst case, it can hold all nodes at the last level)

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offerLast(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> currLevel = new ArrayList<>();

            for(int i =0;i < size ; i++){
                TreeNode node = q.pollFirst();
                currLevel.add(node.val);

                if(node.left != null) q.offerLast(node.left);
                if(node.right != null) q.offerLast(node.right);
            }

            ans.add(currLevel);
        }
        return ans;
    }
}
