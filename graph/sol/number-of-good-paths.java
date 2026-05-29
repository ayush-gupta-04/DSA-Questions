// Intuition : 
// - If two nodes have the same value and there is a larger value between them , 
//   although they are connected by edges but indirectly they are not connected.

// - For a good path, If two nodes have the same value .. we have to ask. Are they Connected or Not ?
//   - If they are connected there will be a good path.
//   - If they are not then no good path is there.
//   - connected means that there is no value between them which is greater then them.

// How to make sure that we don't have a larger value in between.
// - what if we try to build the connections from smaller values to larger values.
// - then if two nodes are connected .. we will be damm sure that there are no values in between which are greater then them.
// - Intuition for DSU : 
//   - i am dynamically building the connections.
//   - i will be constantly asking .. are they connected or not.


// - make adj list.
// - make tree map of valToNodes.
// - for all nodes with the same values .. try to connect them to their neigh only if neigh's vals <= node's vals.
// - now check nodes(with same val) are divides in how many grps.

class Solution {
    static class DS{
        int[] size;
        int[] parent;
        public DS(int n){
            this.size = new int[n+1];
            this.parent = new int[n+1];
            for(int i = 0;i <= n;i++){
                this.size[i] = 1;
                this.parent[i] = i;
            }
        }

        public int findParent(int u){
            if(u == parent[u]){
                return u;
            }
            int p = findParent(parent[u]);
            parent[u] = p;
            return p;
        }

        public int parent(int u){
            return this.parent[u];
        }

        public void union(int u , int v){
            if(isConnected(u,v)) return;

            int pu = findParent(u);
            int pv = findParent(v);

            if(size[pu] > size[pv]){
                size[pu] += size[pv];
                parent[pv] = pu;
            }else{
                size[pv] += size[pu];
                parent[pu] = pv;
            }
        }

        public boolean isConnected(int u, int v){
            return this.findParent(u) == this.findParent(v);
        }
    }
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<List<Integer>> adj = new ArrayList<>();
        TreeMap<Integer , List<Integer>> valToNodes = new TreeMap<>();
        DS ds = new DS(n);

        for(int i = 0;i< n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for(int i = 0;i < n;i++){
            int node = i;
            int val = vals[i];
            valToNodes.computeIfAbsent(val , k -> new ArrayList<>()).add(node);
        }
        int cnt = 0;
        for(int key : valToNodes.keySet()){
            List<Integer> nodes = valToNodes.get(key);

            for(int node : nodes){
                for(int neigh : adj.get(node)){
                    if(vals[neigh] <= vals[node]) ds.union(neigh,node);
                }
            }

            HashMap<Integer, Integer> group = new HashMap<>();
        
            // we are dividing the nodes into groups
            // group : {ultimate parent -> how many nodes have this as their ultimate parent}.
            for(int u : nodes) {
                group.put(ds.findParent(u), group.getOrDefault(ds.findParent(u), 0) + 1);
            }
            
            cnt += nodes.size();
            
            for(int k : group.keySet()) {
                int size = group.get(k);
                cnt += size * (size-1) / 2;
            }
        }
        return cnt;
    }
}
