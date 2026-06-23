// Obv : 
// - Shortest path & unit weight .. it will be a simple BFS shortest path.
// - We can only visit a node twice .. once with blue and once with red.
// - The first time we visit a node with any color edge .. it's with shortest path.

// - since we can visit a node twice .. we will have {node , color of last edge, steps}.
// - since we can visit a node twice .. we will have vis[nodes][2].


class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // red edge = 0, blue edge = 1
        for (int[] re : redEdges) {
            adj.get(re[0]).add(new int[]{re[1], 0}); // 0 for red
        }
        for (int[] bl : blueEdges) {
            adj.get(bl[0]).add(new int[]{bl[1], 1}); // 1 for blue
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        boolean[][] vis = new boolean[n][2]; // vis[node][color]
        Deque<int[]> q = new ArrayDeque<>();

        q.offerLast(new int[]{0,0,0}); // node, color, dist
        q.offerLast(new int[]{0,1,0}); 
        vis[0][0] = true;
        vis[0][1] = true;
        distance[0] = 0;

        while (!q.isEmpty()) {
            int node = q.peekFirst()[0];
            int color = q.peekFirst()[1];
            int dist = q.peekFirst()[2];
            q.pollFirst();

            for (int[] neigh : adj.get(node)) {
                int v = neigh[0];
                int nextCol = neigh[1];
                //i can enter a node only 2 times.
                if (nextCol != color && !vis[v][nextCol]) {
                    //i am visiting the node first time.
                    if(distance[v] == -1){
                        distance[v] = dist + 1;
                    }
                    vis[v][nextCol] = true;
                    q.offer(new int[]{v, nextCol, dist + 1});
                }
            }
        }
        return distance;
    }
}
