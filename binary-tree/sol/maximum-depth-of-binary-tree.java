// ------------- Method 1 : BFS --------------------
// time : N
// space : N


// find it using BFS
class Pair{
    TreeNode node;
    int level;
    Pair(TreeNode node, int level){
        this.node = node;
        this.level = level;
    }
}
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Deque<Pair> q = new ArrayDeque<>();
        q.push(new Pair(root , 1));
        
        int max = 0;
        while(!q.isEmpty()){
            Pair p = q.pollFirst();
            TreeNode node = p.node;
            int level = p.level;

            max = Math.max(max , p.level);

            if(node.left != null) q.offerLast(new Pair(node.left , level + 1));
            if(node.right != null) q.offerLast(new Pair(node.right , level + 1));
        }

        return max;
    }
}




// ------------------ Mathod 2 : DFS Postorder ------------------
// time : N
// spcae : N (recursive stack)


// aggregate ans from bottom to up.
// using DFS postorder
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left,right) + 1;
    }
}
