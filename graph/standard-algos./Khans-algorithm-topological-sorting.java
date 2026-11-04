// ---------------------------- By Khan's Algorithm : BFS ----------------------------
// time : V + E (standard BFS)
// space : 2*V (standard BFS)
//    - V : indegree array
//    - V : queue

// Step 1: Calculate in-degrees of all vertices
// Step 2: Push all vertices with in-degree 0 into a Queue
// Step 3: Process the queue
//    // Reduce the in-degree of all adjacent neighbors
//    // If in-degree becomes 0, add it to the queue
// Step 4: Check for cycles

import java.util.*;

public class Main {
    // Function to perform Topological Sort
    public List<Integer> getTopologicalOrder(List<List<Integer>> adj , int V) {
        int[] inDegree = new int[V];

        // Step 1: Calculate in-degrees of all vertices
        for (int i = 0; i < V; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Step 2: Push all vertices with in-degree 0 into a Queue
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();

        // Step 3: Process the queue
        while (!queue.isEmpty()) {
            int current = queue.pollFirst();
            topoOrder.add(current); // Add to the final linear order

            // Reduce the in-degree of all adjacent neighbors
            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;

                // If in-degree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.offerLast(neighbor);
                }
            }
        }

        // Step 4: Check for cycles
        if (topoOrder.size() != vertices) {
            System.out.println("Graph contains a cycle! Topological sort is impossible.");
            return new ArrayList<>(); // Return empty list
        }

        return topoOrder;
    }
}


// ---------------------------- By DFS -------------------
// time : V + E (standard DFS)
// space : 2*V (standard DFS)


public class Main {

    // Main function for Topological Sort
    public static List<Integer> getTopologicalOrder(List<List<Integer>> adjList, int V) {
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsTopo(i, visited, stack, adjList);
            }
        }

        // Pop elements from the stack to get the topological order
        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pollLast());
        }

        return topoOrder;
    }

    // Helper DFS function
    private static void dfsTopo(int node, boolean[] visited, Deque<Integer> stack, List<List<Integer>> adjList) {
        visited[node] = true;

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfsTopo(neighbor, visited, stack, adjList);
            }
        }

        // Push the current vertex to the stack after all its dependencies are processed
        stack.offerLast(node);
    }
}
