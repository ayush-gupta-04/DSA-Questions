// Time : m^N
//  - worst case .. it tries m possible colors for N vertices.
// Space : N + N + (N + E)
//  - N : for colors array
//  - N : recursion
//  - (N + E) : for adj list

class Solution {
    private boolean isSafe(int node , List<List<Integer>> adj , int[] colors,int c){
        for(int v : adj.get(node)){
            if(colors[v] == c) return false;
        }
        return true;
    }
    public boolean solve(int node, List<List<Integer>> adj ,int[] colors,int N,int m){
        if(node == N){
            //all nodes are colored.
            return true;
        }
        
        
        // for a given node .. try all the possible colors.
        for(int c = 1 ; c <= m ; c++){
            if(isSafe(node,adj,colors,c)){
                colors[node] = c;
                if(solve(node+1,adj,colors,N,m)) return true;
                colors[node] = 0;
            }
        }
        return false;
    }
    boolean graphColoring(int N, int[][] edges, int m) {
        // code here
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < N; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        int[] colors = new int[N];
        return solve(0,adj,colors,N,m);
    }
}
