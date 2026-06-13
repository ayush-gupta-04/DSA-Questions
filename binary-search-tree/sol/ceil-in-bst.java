// time : logN
// space : 1

// If there is conditional movement then i don't need recursion.
// i can just do this with iteration also.

class Solution {
    int findCeil(Node root, int x) {
       int ceil = -1;
       Node curr = root;
       
       while(curr != null){
           if(curr.data == x){
               ceil = x;
               break;
           }
           
           if(x > curr.data){
               curr = curr.right;
           }else{
               ceil = curr.data;
               curr = curr.left;
           }
       }
       
       return ceil;
    }
}
