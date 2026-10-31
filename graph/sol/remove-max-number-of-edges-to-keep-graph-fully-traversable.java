// Sort Edges: Sort the edges in descending order based on their type so that Type 3 edges are processed first.
// Two DSUs: Maintain two separate Disjoint Set Unions (DSU)—one for Alice and one for Bob.
// Process Type 3: Try to union the nodes in both DSUs. If the edge successfully connects two different components for either Alice or Bob, we count it as a required edge.
// Process Type 1 & 2: Try to union in Alice's DSU (Type 1) or Bob's DSU (Type 2). If it connects different components, count it as a required edge.
// Validation: Finally, count the number of connected components for both Alice and Bob. 
//             If either has more than 1 component, the graph isn't fully traversable, so return -1. 
//             Otherwise, the answer is the total number of edges minus the number of required edges.



// time : ElogE
// space : V
class DSU{
    int[] size;
    int[] parent;

    public DSU(int n){
        this.size = new int[n];
        this.parent = new int[n];

        for(int i = 0;i < n ;i++){
            size[i] = 1;
            parent[i] = i;
        }
    }

    int findParent(int u){
        if(u == parent[u]) return u;
        return parent[u] = findParent(parent[u]);
    }

    boolean union(int u, int v){
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv) return false;

        if(size[pu] > size[pv]){
            parent[pv] = pu;
            size[pu] += size[pv];
        }else{
            parent[pu] = pv;
            size[pv] += size[pu];
        }
        return true;
    }
}
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (x,y) -> Integer.compare(y[0], x[0]));

        DSU aDSU = new DSU(n+1);
        DSU bDSU = new DSU(n+1);
        int cnt = 0;

        for(int[] e : edges){
            int type = e[0];
            int u = e[1];
            int v = e[2];
            
            if(type==3){  // both
                boolean aliceConnect = aDSU.union(u, v);
                boolean bobConnect = bDSU.union(u, v);
                if(aliceConnect || bobConnect) cnt++;
            }else if(type==2){  // bob
                if(bDSU.union(u, v)) cnt++;
            }else{  // alice
                if(aDSU.union(u, v)) cnt++;
            }
        }

        int a = 0;
        int b = 0;
        for(int i = 1; i <= n; i++){
            if(i == aDSU.findParent(i)) a++;
            if(i == bDSU.findParent(i)) b++;
        }
        if(a != 1 || b != 1) return -1;
        // System.out.println(a + " " + b);

        return edges.length-cnt;
    }
}
