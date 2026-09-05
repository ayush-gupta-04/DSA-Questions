// time : N
// space : N

// we will have a hashmap of col -> node.
// we want bottom most .. therefore we processed nodes from top to bottom..level wise.
// if i have mapped col -> node1 .. and i saw col -> node2 .. i will defenately update the mapp to col -> node2,
//   because my priority is bottom node.

// just the same as top-view.


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

    public ArrayList<Integer> bottomView(Node root) {
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

            colToNode.put(col, node.data);   // do not check here, it will make sure it updates to the bottom one.
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);

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
