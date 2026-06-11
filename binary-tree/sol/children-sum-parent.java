// time : N
// space : H (recursive)
// postorder.

class Solution {
    boolean isLeaf(Node node){
        return node.left == null && node.right==null;
    }
    int solve(Node node){
        if(node==null) return 0;
        
        int left = solve(node.left);  // left child value.
        int right = solve(node.right); // right child value.
        
        if(left==-1 || right==-1) return -1;
        
        // no check if leaf.
        if(isLeaf(node)){
            return node.data;
        }
        
        // check if property satisfy.
        if(left + right != node.data) return -1;
        return node.data;
    }
    public boolean isSumProperty(Node root) {
        return solve(root) != -1;
    }
}
