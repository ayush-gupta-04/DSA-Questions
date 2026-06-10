// time : 2logN + N
// space : N
// lefts -> only lefts .. no root .. no leaf.
// rights -> only rights .. no root .. no leaf.
// leaf -> only leaves .. no root .. no left .. no rights.

// Pitfall : 
// when adding left, if a node(not leaf) doesn't have a left but have a right .. 
//      we will add right and then go left..left..left.


class Solution {
    void addLeaf(Node node,ArrayList<Integer> ans){
        if(node == null) return;
        if(isLeaf(node)){
            ans.add(node.data);
            return;
        }
        addLeaf(node.left,ans);
        addLeaf(node.right,ans);
    }
    
    boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }
    
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        // add root.
        ans.add(root.data);
        
        Node left = root.left;
        Node right = root.right;
        // we will go left left left ...
        // if no left then a right then again left left left ...
        while(left != null && !isLeaf(left)){
            ans.add(left.data);
            if(left.left==null) left = left.right;
            else left = left.left;
        }
        
        // add leaves .. exclude root.
        addLeaf(root.left,ans);
        addLeaf(root.right,ans);
        
        ArrayList<Integer> temp = new ArrayList<>();
        // we will go right right right ...
        // if no right then a left then again right right right ...
        while(right != null && !isLeaf(right)){
            temp.add(right.data);
            if(right.right==null) right = right.left;
            else right = right.right;
        }
        Collections.reverse(temp);
        ans.addAll(temp);
    
        return ans;
    }
}
