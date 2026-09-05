// time : N
// space : N

// we will have a HashMap of col -> node.
// we want top nodes .. therefore we processed nodes from top to bottom..level wise.

class Solution {
    // Helper pair without redundant row tracking
    static class QueueNode {
        Node node;
        int col;

        QueueNode(Node node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> colToNode = new HashMap<>();
        Deque<QueueNode> queue = new ArrayDeque<>();

        queue.offer(new QueueNode(root, 0));

        int minCol = 0;
        int maxCol = 0;

        while (!queue.isEmpty()) {
            QueueNode curr = queue.poll();
            Node node = curr.node;
            int col = curr.col;

            // First node to reach a column in BFS is guaranteed to be the top-most
            if (!colToNode.containsKey(col)) {
                colToNode.put(col, node.data);
                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);
            }

            if (node.left != null) {
                queue.offer(new QueueNode(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, col + 1));
            }
        }

        // Linear retrieval from leftmost to rightmost column
        for (int c = minCol; c <= maxCol; c++) {
            if (colToNode.containsKey(c)) {
                result.add(colToNode.get(c));
            }
        }

        return result;
    }
}
