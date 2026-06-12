// ------------------ Better Approach -----------------------
// time : N
// space : N (worst case)

// travel levelwise and populate the next pointer of every node.
class Solution {
    public Node connect(Node root) {
        if(root==null) return root;
        Deque<Node> q = new ArrayDeque<>();
        q.offerLast(root);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0;i < size ; i++){
                Node node = q.pollFirst();

                if(node.left != null) q.offerLast(node.left);
                if(node.right != null) q.offerLast(node.right);

                if(i != size-1){
                    node.next = q.peekFirst();
                }
            }
        }
        return root;
    }
}



// ----------------- Optimal Approach -------------------
// time : N
// space : 1

// travel level wise and connect the children of every node.

class Solution {
    public Node connect(Node root) {
        if(root==null) return root;
        
        Node start = root;
        while(start.left != null){
            Node move = start;
            while(move != null){
                // connect children
                move.left.next = move.right;
                if(move.next != null){ // connect the bridge.
                    move.right.next = move.next.left;
                }
                move = move.next;
            }
            start = start.left;
        }
        return root;
    }

}
