// time : N amortised.
// space : 1

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        
        TreeNode curr = root;

        while(curr != null){
            if(curr.left == null){
                // no left.
                list.add(curr.val);
                curr = curr.right;
            }else{
                // left exist.
                // find thread.
                // if thread exist .. means we have visited left .. remove thread, move to right.
                // else means we have not explored left .. make thread, print node, move left.

                TreeNode last = curr.left;
                while(last.right != null && last.right != curr){
                    last = last.right;
                }

                if(last.right == null){
                    // no thread.
                    last.right = curr;
                    list.add(curr.val);
                    curr = curr.left;
                }else{
                    // thread exist already.
                    last.right = null;
                    curr = curr.right;
                }
            }
        }

        return list;
    }
}
