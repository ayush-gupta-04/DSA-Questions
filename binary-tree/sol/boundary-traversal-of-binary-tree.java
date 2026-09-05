// time : 2logN + N
// space : N
// lefts -> only lefts .. no root .. no leaf.
// rights -> only rights .. no root .. no leaf.
// leaf -> only leaves .. no root .. no left .. no rights.

// Pitfall : 
// when adding left, if a node(not leaf) doesn't have a left but have a right .. 
//      we will add right and then go left..left..left.


class Solution {
    private boolean isLeaf(Node node){
        return (node.left == null && node.right == null);
    }
    private void addLeftNodes(Node node, ArrayList<Integer> list){
        // we will go left left left ...
        // if no left then a right then again left left left ...
        while(node != null && !isLeaf(node)){
            list.add(node.data);
            if(node.left != null) node = node.left;
            else node = node.right;
        }
    }
    private void addRightNodes(Node node, ArrayList<Integer> list){
        // we will go right right right ...
        // if no right then a left then again right right right ...
        while(node != null && !isLeaf(node)){
            list.add(node.data);
            if(node.right != null) node = node.right;
            else node = node.left;
        }
    }
    private void addLeafNodes(Node node , ArrayList<Integer> list){
        if(node == null) return;
        
        if(isLeaf(node)){
            list.add(node.data);
            return;
        }
        addLeafNodes(node.left, list);
        addLeafNodes(node.right, list);
    }
    public ArrayList<Integer> boundaryTraversal(Node root) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        
        
        list.add(root.data);            // add root to list;
        addLeftNodes(root.left, list);  // add Left nodes
        
        addLeafNodes(root.left, list);  // add leaf nodes .. exclude root.
        addLeafNodes(root.right, list);  // add leaf nodes .. exclude root.
        
        addRightNodes(root.right, temp);  // add right nodes to temp.
        Collections.reverse(temp);        // reverse temp .. add elements of temp to list.
        list.addAll(temp);
        return list;
    }
}
