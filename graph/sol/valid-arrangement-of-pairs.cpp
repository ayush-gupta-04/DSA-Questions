// It looks like we can visit a node multiple times but we are only using an edge once.
// It is Euler circuit problem .. Hierholzer's Algorithm
// Algo : 
// -> Make an Adj List , Which will be a map of { int -> queue<int> } .... 
//          queue because we will have to remove the edge once used.
// -> A directed graph has an Eulerian path iff:
//    1. Exactly one node has ( outDegree = inDegree + 1 ) ...... start node.
//    2. Exactly one node has ( intDegree = outDegree + 1 ) ...... end node.
//    3. All other nodes have equal in and out degrees
// -> If no such Start or End node exist ... then path is a cycle , We can choose any start node or end node.
// -> Apply DFS from the Start node.
// -> For a node 'u' .. visit it's neigh and pop them ...... because if i have choosen to visit an egde (u -> v)
//          then i will remove it .. because i can only visit an edge once.
//          but since i can visit a node multiple times .. i cannot have a visited array.
// -> For a recursive call of node 'u' .. before coming out of the call .. just push it to the vector<int> result.
//          - We only record u after all its edges are exhausted
//          - We Proceed PostOrder .. 
//          - Why this works (deep intuition) ??
//          - When DFS reaches a node u:
//            - If 'u' still has outgoing edges → we are not done with 'u'.
//            - If 'u' has NO outgoing edges → 'u' must appear at the end of the Eulerian path.

// -> We need to reverse the path to get the original path.





import java.util.*;

class Solution {
    private List<Integer> result = new ArrayList<>();

    private void dfs(int node, Map<Integer, Deque<Integer>> adj) {
        Deque<Integer> neigh = adj.get(node);
        while (neigh != null && !neigh.isEmpty()) {
            int nextNode = neigh.pollFirst();
            dfs(nextNode, adj);
        }
        result.add(node);
    }

    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();

        // Build adjacency list and track in/out degrees
        for (int[] pair : pairs) {
            int u = pair[0];
            int v = pair[1];

            adj.computeIfAbsent(u, k -> new ArrayDeque<>()).add(v);
            outDegree.put(u, outDegree.getOrDefault(u, 0) + 1);
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }

        // Find starting node: outDegree - inDegree == 1
        int startNode = pairs[0][0];
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) - inDegree.getOrDefault(node, 0) == 1) {
                startNode = node;
                break;
            }
        }

        // Hierholzer's DFS traversal
        dfs(startNode, adj);

        // Reverse the post-order sequence
        Collections.reverse(result);

        // Construct edge pairs
        int n = pairs.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i][0] = result.get(i);
            ans[i][1] = result.get(i + 1);
        }

        return ans;
    }
}
