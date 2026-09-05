public class Codec {
    private static final String DELIMITER = ",";
    private static final String NULL_MARKER = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();   // we can have null in the queue.

            if (curr == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
            } else {
                sb.append(curr.val).append(DELIMITER);
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        // Remove trailing delimiter
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] tokens = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < tokens.length) {
            TreeNode parent = queue.poll();   // we will attach children to this .. so parent can't be null.

            // Process left child
            if (!tokens[i].equals(NULL_MARKER)) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(tokens[i]));
                parent.left = leftNode;
                queue.offer(leftNode);
            }
            i++;

            // Process right child
            if (i < tokens.length && !tokens[i].equals(NULL_MARKER)) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(tokens[i]));
                parent.right = rightNode;
                queue.offer(rightNode);
            }
            i++;
        }
        return root;
    }
}
