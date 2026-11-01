// ==========================================
// INTUITION
// ==========================================
// 1. Baseline Target: Find the minimum possible weight to connect all vertices (baseWeight) using standard Kruskal’s algorithm.
// 2. Critical Edges: If removing an edge makes it impossible to achieve baseWeight (or disconnects the graph), that edge must be present in every MST.
// 3. Pseudo-Critical Edges: If an edge is not critical, but forcing it into the tree still achieves baseWeight, that edge belongs to at least one valid MST.
// 4. Feasibility: Since E <= 200, running Kruskal’s algorithm at most twice per edge (once to exclude, once to force-include) easily runs well within time limits.

// ==========================================
// APPROACH
// ==========================================
// 1. Preserve Original Indices: Store each edge as [u, v, weight, originalIndex] before sorting, since the final result requires original indices.
// 2. Sort by Weight: Sort all edges in ascending order of their weights to support Kruskal’s greedy edge selection.
// 3. Compute Base MST Weight: Run Kruskal's algorithm using a Disjoint Set Union (DSU) structure to establish baseWeight.
// 4. Check Critical Edges (Exclusion): Run Kruskal's while skipping the current edge. If the total weight exceeds baseWeight or the graph disconnects, mark it as critical.
// 5. Check Pseudo-Critical Edges (Inclusion): If an edge is not critical, pre-add it to the DSU and run Kruskal's. If the resulting weight equals baseWeight, mark it as pseudo-critical.
// 6. Return Result: Package and return [criticalEdges, pseudoCriticalEdges].

class Solution {

    static class DSU {
        private final int[] parent;
        private final int[] size;

        public DSU(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int findParent(int x) {
            if(x == parent[x]) return x;
            return parent[x] = findParent(parent[x]);
        }

        public boolean union(int x, int y) {
            int pu = findParent(x);
            int pv = findParent(y);

            if (pu == pv) return false;

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

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;

        int[][] sortedEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            sortedEdges[i][0] = edges[i][0];
            sortedEdges[i][1] = edges[i][1];
            sortedEdges[i][2] = edges[i][2];
            sortedEdges[i][3] = i;
        }

        Arrays.sort(sortedEdges, (a, b) -> Integer.compare(a[2], b[2]));

        int baseWeight = buildMST(n, sortedEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            // Exclude the current edge
            int weightWithout = buildMST(n, sortedEdges, i, -1);

            if (weightWithout > baseWeight) {
                // If weight increases or graph disconnects, it's critical
                critical.add(sortedEdges[i][3]);
            } else {
                // Force-include the current edge
                int weightWith = buildMST(n, sortedEdges, -1, i);
                if (weightWith == baseWeight) {
                    pseudoCritical.add(sortedEdges[i][3]);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int buildMST(int n, int[][] edges, int ex, int in) {
        DSU ds = new DSU(n);
        int totalWt = 0;
        int cnt = 0;

        // Force include edge
        if (in != -1) {
            int u = edges[in][0];
            int v = edges[in][1];
            int wt = edges[in][2];
            ds.union(u, v);
            totalWt += wt;
            cnt++;
        }

        // Greedily pick remaining edges
        for (int i = 0; i < edges.length; i++) {
            if (i == ex || i == in) {
                continue;
            }

            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            if (ds.union(u, v)) {
                totalWt += wt;
                cnt++;
            }
        }

        // A valid spanning tree on n nodes must have exactly n - 1 edges
        return cnt == n - 1 ? totalWt : Integer.MAX_VALUE;
    }
}
