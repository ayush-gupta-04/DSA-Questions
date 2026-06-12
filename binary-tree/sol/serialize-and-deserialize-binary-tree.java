public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "n";

        Deque<TreeNode> q = new ArrayDeque<>();
        StringBuilder str = new StringBuilder("");
        q.offerLast(root);
        str.append(Integer.toString(root.val) + ",");

        while(!q.isEmpty()){
            TreeNode node = q.pollFirst();   //node cannot be null.
            
            if(node.left == null) str.append("n,");
            else str.append(Integer.toString(node.left.val) + ",");
            if(node.right == null) str.append("n,");
            else str.append(Integer.toString(node.right.val) + ",");

            if(node.left != null) q.offerLast(node.left);
            if(node.right != null) q.offerLast(node.right);
        }
        str.deleteCharAt(str.length() - 1);
        return new String(str);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("n")) return null;

        Deque<TreeNode> Q = new ArrayDeque<>();
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Q.offerLast(root);

        for(int i = 1 ; i < nodes.length ; i++){
            TreeNode node = Q.pollFirst();
            if(!nodes[i].equals("n")){
                TreeNode leftNode = new TreeNode(Integer.parseInt(nodes[i]));
                node.left = leftNode;
                Q.offerLast(leftNode);
            }

            if(!nodes[++i].equals("n")){
                TreeNode rightNode = new TreeNode(Integer.parseInt(nodes[i]));
                node.right = rightNode;
                Q.offerLast(rightNode);
            }
        }

        return root;
    }
}
