// We have to keep a parent.
// If neigh is parent ... SKIP SKIP SKIP.
// Call DFS for unvisited neigh only.
// for Low : 
//    - If unvisited neigh : low[node] will be min of low[neigh]
//    - If vis neigh       : low[node] will be min of tin[neigh]


// IMP definition
// tin[] -> Stores the time of intersion during DFS.
// low[] -> Minimum low with "ALL" neigh nodes (except parent & Visited neigh)

// IF low[neigh] < tin[node]  .....   I can reach (before node) from neigh using any other edge .... node not an articulation point.



class Solution {
    static void dfs(int node,int parent, List<List<Integer>> adj, int[] tin, int[] low, int time, HashSet<Integer> set){
        tin[node] = time;
        low[node] = time;
        int child = 0;

        for(int neigh : adj.get(node)){
            if(neigh == parent) continue;

            if(tin[neigh] == -1){
                child++;
                dfs(neigh, node, adj, tin, low, time+1, set);
                low[node] = Math.min(low[node], low[neigh]);
                
                // i cannot reach before node from neigh node.
                if(low[neigh] >= tin[node] && parent != -1){
                    set.add(node);
                }
            }else{
                low[node] = Math.min(low[node], tin[neigh]);
            }
        }
        
        
        // 
        if(parent == -1 && child > 1){   // if 1 child then it is not an articulation point.
            set.add(node);
        }
    }
    static List<List<Integer>> buildGraph(int n, int[][] edges){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
    static ArrayList<Integer> articulationPoints(int n, int[][] edges) {
        List<List<Integer>> adj = buildGraph(n, edges);
        HashSet<Integer> set = new HashSet<>();

        int[] tin = new int[n];
        int[] low = new int[n];

        Arrays.fill(tin, -1);   // to check for vis.
        Arrays.fill(low, -1);

        for(int node = 0; node < n; node++){
            if(tin[node] != -1) continue;
            dfs(node, -1, adj, tin, low, 0, set);
        }
        
        ArrayList<Integer> ans = new ArrayList<>(set);
        if(ans.isEmpty()){
            ans.add(-1);
            return ans;
        } 
        Collections.sort(ans);
        return ans;
    }
}
