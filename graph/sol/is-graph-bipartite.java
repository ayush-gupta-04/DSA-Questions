class Solution {
    boolean dfs(int node , int c, int[] col , int[][] adj){
        col[node] = c;

        for(int neigh : adj[node]){
            if(col[neigh] == -1){
                if(!dfs(neigh , c^1 , col , adj)) return false;
            }
            if(col[neigh] == col[node]) return false;
        }
        return true;
    }
    public boolean isBipartite(int[][] adj) {
        int n = adj.length;
        int[] col = new int[n];
        Arrays.fill(col,-1);

        for(int node = 0; node < n; node++){
            if(col[node] == -1){
                if(!dfs(node,0,col,adj)) return false;
            }
        }

        return true;
    }
}
