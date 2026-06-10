// time : N
// space : N

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        Deque<TreeNode> d = new ArrayDeque<>();

        d.offerLast(root);
        int level = 0;

        while(!d.isEmpty()){
            int size = d.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i < size ; i++){  
                if(level%2 == 0){
                    TreeNode node = d.pollFirst();
                    list.add(node.val);
                    if(node.left!=null) d.offerLast(node.left);
                    if(node.right!=null) d.offerLast(node.right);
                }else{
                    TreeNode node = d.pollLast();
                    list.add(node.val);
                    if(node.right!=null) d.offerFirst(node.right);
                    if(node.left!=null) d.offerFirst(node.left);
                }
            }
            level++;
            ans.add(list);
        }

        return ans;
    }
}
