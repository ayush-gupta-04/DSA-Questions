import java.util.*;

class Solution {
    public int countSCCs(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Step 1: Store vertices in a stack based on their finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, adj, visited, stack);
            }
        }
        
        // Step 2: Create the reversed (transposed) graph
        List<List<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            revAdj.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                revAdj.get(neighbor).add(i); // Reverse the direction of the edge
            }
        }
        
        // Step 3: Perform DFS on the reversed graph using the stack order
        Arrays.fill(visited, false);
        int sccCount = 0;
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            
            // If the node is unvisited, it belongs to a new SCC
            if (!visited[node]) {
                dfs2(node, revAdj, visited);
                sccCount++; 
            }
        }
        
        return sccCount;
    }
    
    private void dfs1(int node, List<List<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, adj, visited, stack);
            }
        }
        // Push to stack after visiting all reachable descendants (post-order)
        stack.push(node);
    }
    
    private void dfs2(int node, List<List<Integer>> revAdj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : revAdj.get(node)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, revAdj, visited);
            }
        }
    }
}
