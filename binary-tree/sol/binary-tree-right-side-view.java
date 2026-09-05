// ------------------ Method 1 : BFS ----------------
// time : N
// space : N

// do a level-order traversal ..
// for each node just identify if that node is the rightmost in the row.
// if yes then add it to the ans.

class Solution {
    public ArrayList<Integer> rightView(Node root) {
        // code here
        Deque<Node> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root ==null) return list;
        
        q.offerLast(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i = 0; i < size; i++){
                Node node = q.pollFirst();
                
                // add last node of every level to the list;
                if(i==size-1) list.add(node.data);
                
                if(node.left != null) q.offerLast(node.left);
                if(node.right != null) q.offerLast(node.right);
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
