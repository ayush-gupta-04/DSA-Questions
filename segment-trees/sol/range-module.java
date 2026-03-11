//--------------------------- Approach : Dynamic Segment Tree -------------------------
// In normal Lazy Propagation.
// -> lazy != 0 -> There is a pending update that I must apply to MYSELF (seg[idx]) right now, and then pass to my
//                  children.
// Here,
// lazy != -1 : I the parent is up-to-date by i have to pass down to the children.
// propagateDown : 
// -> It is called when we have to go left and right.
// -> It creates children nodes if not exist.
// -> It updates the value of children.
// -> Pass lazy to them to propagate later.
//
// When nodes are created ?
// -> When we need to go down for addRange() or removeRange() or queryRange()....we create the children nodes.


class Node{
    boolean tracked;
    Node left,right;
    int lazy = -1;
}
class RangeModule {
    Node root; 
    int min = 1;
    int max = (int)1e9; 
    public RangeModule() {
        this.root = new Node();
    }

    private void propagateDown(Node node){
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();
        
        if(node.lazy != -1){
            node.left.lazy = node.lazy;
            node.right.lazy = node.lazy;

            node.left.tracked = (node.lazy == 1);
            node.right.tracked = (node.lazy == 1);
        }
        node.lazy = -1;
        return;
    }
    
    public void addRange(int left, int right) {
        add(root , min , max , left , right-1);
    }
    private void add(Node node , int low ,int high , int l,  int r){
        // no overlap.
        if(r < low || high < l){
            return;
        }
        // complete overlapp.
        if(l <= low && high <= r){
            node.tracked = true;
            node.lazy = 1;
            return;

        }
        propagateDown(node);
        int m = low + (high - low)/2;
        add(node.left , low ,  m , l , r);
        add(node.right , m + 1 , high , l , r);
        node.tracked = node.left.tracked && node.right.tracked;
    }
    
    public boolean queryRange(int left, int right) {
       return query(root , min , max , left , right-1);
    }
    private boolean query(Node node , int low ,int high , int l,  int r){
        if(r < low || high < l){  // no overlapp
            return true;
        }
        if(l <= low && high <= r){    // complete overlapp.
            return node.tracked;
        }
        propagateDown(node);
        int m = low + (high - low)/2;
        boolean leftAns = query(node.left , low ,  m , l , r);
        boolean rightAns = query(node.right , m + 1 , high , l , r);
        return leftAns && rightAns;
    }
    
    public void removeRange(int left, int right) {
        remove(root , min , max , left , right-1);
    }
    private void remove(Node node , int low ,int high , int l,  int r){
        // no overlap.
        if(r < low || high < l){    // no overlapp.
            return;
        }
        if(l <= low && high <= r){    // complete overlapp.
            node.tracked = false;
            node.lazy = 0;
            return;
        }
        propagateDown(node);
        int m = low + (high - low)/2;
        remove(node.left , low ,  m , l , r);
        remove(node.right , m + 1 , high , l , r);
        node.tracked = node.left.tracked && node.right.tracked;
    }
}
