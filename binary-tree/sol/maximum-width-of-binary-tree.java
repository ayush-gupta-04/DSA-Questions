// time : N
// space : N

// What if i do indexing of evenry nodes on a level.
// we can easily find the distance between the start and end node.
// we will do indexing from 0 -> ..
// parent (idx) => left (2*idx) .. right(2*idx + 1)
class Data{
    TreeNode node;
    int idx;
    public Data(TreeNode node , int idx){
        this.node = node;
        this.idx = idx;
    }
}
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Deque<Data> q = new ArrayDeque<>();
        q.offerLast(new Data(root ,0));
        int max = 0;
        while(!q.isEmpty()){
            int size = q.size();
            int start = -1;
            int end = -1;

            for(int i = 0;i < size ;i++){
                TreeNode node = q.peekFirst().node;
                int idx = q.peekFirst().idx;
                q.pollFirst();

                if(i==0) start = idx;
                if(i==size-1) end = idx;

                if(node.left != null) q.offerLast(new Data(node.left , 2*idx));
                if(node.right != null) q.offerLast(new Data(node.right , 2*idx + 1));
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
