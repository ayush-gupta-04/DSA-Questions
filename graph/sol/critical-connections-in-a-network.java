// We have to keep a parent.
// If neigh is parent ... SKIP SKIP SKIP.
// Call DFS for unvisited neigh only.
// update low of curr node with "ALL" neigh.


// IMP definition
// tin[] -> Stores the time of intersion during DFS.
// low[] -> Minimum low with "ALL" neigh nodes (except parent)

class Solution {
    void findBridgesDFS(int node,int parent, List<List<Integer>> adj, int[] tin, int[] low, int time, List<List<Integer>> ans){
        tin[node] = time;
        low[node] = time;

        for(int neigh : adj.get(node)){
            if(neigh == parent) continue;

            if(tin[neigh] == -1){
                findBridgesDFS(neigh, node, adj, tin, low, time+1, ans);
            }

            // Update low of curr node with "ALL" neigh.
            low[node] = Math.min(low[node], low[neigh]);
            if(low[neigh] > tin[node]){
                ans.add(Arrays.asList(node, neigh));
            }
        }
    }
    List<List<Integer>> buildGraph(int n, List<List<Integer>> edges){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(List<Integer> edge : edges){
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        List<List<Integer>> adj = buildGraph(n, edges);
        List<List<Integer>> ans = new ArrayList<>();

        int[] tin = new int[n];
        int[] low = new int[n];

        Arrays.fill(tin, -1);   // to check for vis.
        Arrays.fill(low, -1);

        for(int node = 0; node < n; node++){
            if(tin[node] != -1) continue;
            findBridgesDFS(node, -1, adj, tin, low, 0, ans);
        }
        return ans;
    }
}
