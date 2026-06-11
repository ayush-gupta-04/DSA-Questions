// time : N
// space : N

// do a level-order traversal ..
// for each node just identify if that node is the rightmost in the row.
// if yes then add it to the ans.

class Data{
    TreeNode node;
    int row;
    int col;
    public Data(TreeNode node , int row, int col){
        this.node = node;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<Data> q = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        if(root ==null) return list;

        q.offerLast(new Data(root,0,0));

        while(!q.isEmpty()){
            TreeNode node = q.peekFirst().node;
            int row = q.peekFirst().row;
            int col = q.peekFirst().col;
            q.pollFirst();

            if(node.left != null) q.offerLast(new Data(node.left , row + 1, col-1));
            if(node.right != null) q.offerLast(new Data(node.right , row + 1, col+1));

            // check to see if this node is last in the row.
            if(q.isEmpty() || q.peekFirst().row == row + 1){
                list.add(node.val);
            }
        }

        return list;
    }
}
