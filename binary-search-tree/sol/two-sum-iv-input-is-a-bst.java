// time : N
// space : 2*H
// - H for stack size...which will store a max of height.
class Solution {
    class Iterator{
        Deque<TreeNode> ASC;
        Deque<TreeNode> DSC;
        Iterator(TreeNode root){
            this.ASC = new ArrayDeque<>();
            this.DSC = new ArrayDeque<>();
            this.pushLeft(root);
            this.pushRight(root);
        }
        int next(){
            TreeNode node = this.ASC.pollLast();
            this.pushLeft(node.right);
            return node.val;
        }
        int before(){
            TreeNode node = this.DSC.pollLast();
            this.pushRight(node.left);
            return node.val;
        }
        boolean hasNext(){
            return !this.ASC.isEmpty();
        }
        boolean hasBefore(){
            return !this.DSC.isEmpty();
        }
        void pushLeft(TreeNode node){
            //for ascending.
            while(node != null){
                this.ASC.offerLast(node);
                node = node.left;
            }

        } 
        void pushRight(TreeNode node){
            //for descending.
            while(node != null){
                this.DSC.offerLast(node);
                node = node.right;
            }
        }
        
    }
    public boolean findTarget(TreeNode root, int k) {
        if(root == null){
            return false;
        }
        Iterator it = new Iterator(root);
        int left = it.next();
        int right = it.before();
        while(left < right){
            int sum = left + right;
            if(sum == k){
                return true;
            }
            else if(sum > k){
                right = it.before();
            }else{
                left = it.next();
            }
        }
        return false;
    }
}
