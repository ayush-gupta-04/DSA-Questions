// --------------------- My - Approach -------------------
// time : N*N
// space : N*N


// i will run a loop from num 0 to N*N-1
// i will find the nodeNo of num.
// i will connect num with any visited neigh of num ... will use Disjoint Set.
// make num vis.
// now check if node 0 and node N*N-1 are connected finally or not.
// if connected .. break;

class Solution {
    class DSU{
        private int[] size;
        private int[] parent;
        
        public DSU(int n){
            this.size = new int[n+1];
            this.parent = new int[n+1];
            for(int i = 0;i <= n;i++){
                this.size[i] = 1;
                this.parent[i] = i;
            }
        }

        public int findParent(int u){
            if(u == this.parent[u]){
                return u;
            }
            return this.parent[u] = this.findParent(parent[u]);
        }

        public int parent(int u){
            return this.parent[u];
        }
        public int size(int u){
            return this.size[u];
        }

        public void union(int u , int v){
            if(isConnected(u,v)) return;

            int pu = findParent(u);
            int pv = findParent(v);

            if(size[pu] > size[pv]){
                this.size[pu] += this.size[pv];
                this.parent[pv] = pu;
            }else{
                this.size[pv] += this.size[pu];
                this.parent[pu] = pv;
            }
        }

        public boolean isConnected(int u, int v){
            return this.findParent(u) == this.findParent(v);
        }
    }
    boolean valid(int r, int c , int n){
        return (r >= 0 && r < n && c >= 0 && c < n);
    }
    public int swimInWater(int[][] grid) {
        int[] dr = {0,0,1,-1};
        int[] dc = {1,-1,0,0};
        int n = grid.length;
        DSU ds = new DSU(n * n + 1);

        HashMap<Integer,Integer> map = new HashMap<>();   // number to nodeNo of Matrix.
        int[][] vis = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j < n ; j++){
                int nodeNo = i*n + j;
                map.put(grid[i][j] , nodeNo);
            }
        }

        for(int i = 0 ; i < n*n ; i++){
            int nodeNo = map.get(i);
            int r = nodeNo/n;
            int c = nodeNo%n;
            vis[r][c] = 1;
            for(int k = 0; k < 4 ; k++){
                int nr = r + dr[k];
                int nc = c + dc[k];
                if(valid(nr,nc,n) && vis[nr][nc] == 1){
                    int adj = grid[nr][nc];
                    //union of ith and it's adjacent.
                    ds.union(map.get(i) , map.get(adj));
                }
            }
            if(ds.isConnected(n*n-1 , 0)){
                return i;
            }
        }
        return -1;
    }
}
