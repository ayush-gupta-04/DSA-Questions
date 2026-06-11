// ------------------ Method 1 : BFS ----------------
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



// ---------------- Method 2 : DFS --------------


// time : N
// space : logN

// traversal : node -> right -> left.
// we are greedily adding nodes from the right.
// if we travel to the left .. we already have added the rightmost from that level..so won't be added.
class Solution {
    public void rightView(TreeNode node , int level , List<Integer> list){
        if(node == null){
            return;
        }

        if(level == list.size()){
            list.add(node.val);
        }

        rightView(node.right,level + 1,list);
        rightView(node.left,level + 1,list);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightView(root,0,list);
        return list;
    }
}
