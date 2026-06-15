
// time : N
// space : H


class Solution {
    static class Data{
        int size;
        boolean isBST;
        int max; 
        int min;
        
        public Data(){
            this.size = 0;
            this.isBST = true;
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
        }
        
        public Data(int size,boolean isBST,int min , int max){
            this.size = size;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    static Data fun(Node node, int[] maxSize){
        if(node == null) return new Data();
        
        Data left = fun(node.left, maxSize);
        Data right = fun(node.right, maxSize);
        
        if(left.isBST && right.isBST){
            // check if this node is also bst.
            if(node.data > left.max && node.data < right.min){
                maxSize[0] = Math.max(maxSize[0] , left.size + right.size + 1);
                return new Data(
                    left.size + right.size + 1,
                    true,
                    Math.min(node.data , left.min),
                    Math.max(node.data , right.max));
            }else{
                return new Data(
                    left.size + right.size + 1,
                    false,
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE);
            }
        }
        
        return new Data(
            left.size + right.size + 1,
            false,
            Integer.MAX_VALUE,
            Integer.MIN_VALUE);
    }
    static int largestBst(Node root) {
        int[] maxSize = {0};
        fun(root,maxSize);
        return maxSize[0];
    }
}
