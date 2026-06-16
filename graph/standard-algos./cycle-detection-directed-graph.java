// --------------------------- BFS : Topo Sort --------------------------
// time : V + E
// space : 2*V

// Intuition : 
// - If i apply topo sort in DAG, i will visit every node.
// - If i am not abe to visit every node, it's not a DAG, cycle is there.

// Step 1: Calculate in-degree (number of incoming edges) for each vertex.
// Step 2: Push all vertices with in-degree 0 into a Queue
// Step 3: Process vertices from the queue
//     // Reduce the in-degree of all adjacent neighbors
//     // If in-degree becomes 0, add it to the queue
//  // Step 4: If visited count doesn't equal total vertices, there's a cycle.

public class Main{
    // it will take care of the dis-connected components.
    public boolean hasCycle(List<List<Integer>> adj , int V) {
        int[] inDegree = new int[V];

        // Step 1: Calculate in-degree (number of incoming edges) for each vertex
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Step 2: Push all vertices with in-degree 0 into a Queue
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        // Step 3: Process vertices from the queue
        int visitedCount = 0; // Tracks how many nodes we successfully sorted

        while (!queue.isEmpty()) {
            int current = queue.pollFirst();
            visitedCount++; // Increment count of visited nodes

            // Reduce the in-degree of all adjacent neighbors
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;

                // If in-degree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.offerLast(neighbor);
                }
            }
        }

        // Step 4: If visited count doesn't equal total vertices, there's a cycle!
        return visitedCount != V;
    }
}












// ------------------------ DFS ------------------------------

// time : V + E
// space : 3V

// If a node is already visited, then it may get visited from another path.
// If a node is visited and it is in the same path, it's a cycle.
// - we will have a vis[]
// - we will have a pathVis[]  .. tracks the current path



import java.util.*;

public class Main{
    // for dis-connected components of a graph.
    public boolean isCycle(List<List<Integer>> adj, int N, int src){
        boolean[] vis = new boolean[N];
        boolean[] pathVis = new boolean[N];

        for(int i = 0;i < N;i++){
            if(!vis[i]){
                boolean doesCycleExist = dfs(adj, i, vis, pathVis);
                if(doesCycleExist) return true;
            }
        }
        return false;
    }

    // main logic to check for cycle.
    public boolean dfs(List<List<Integer>> adj, int node, boolean[] vis, boolean[] pathVis){
        vis[node] = true;
        pathVis[node] = true;

        for(int neigh : adj.get(node)){
            //if a is not visited
            if(!vis[neigh]){
                if(dfs(adj, neigh, vis,pathVis)){
                    return true;
                };
            }

            // if neigh is visited on the same path :
            //   Then it's a cycle.
            if(pathVis[neigh]){
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
}
