// ---------------- Brute-Force -----------------
// time : N + NlogN
// space : N + H

// - find the inorder traversal.
// - find the number just greater than the k.


// --------------- Optimal Approach -------------
// time : logN
// space : 1


class Solution {
    public int inOrderSuccessor(Node root, Node k) {
        Integer succ = null;
        
        Node curr = root;
        while(curr != null){
            if(curr.data <= k.data){  // i must go to the right.
                curr = curr.right;
            }else{                    // this could be my ans .. i will explore smaller also.
                succ = curr.data;
                curr = curr.left;
            }
        }
        return succ==null ? -1 : succ;
    }
}
