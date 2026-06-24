class Solution {
    private class Disjoint {
        int[] size;
        int[] parent;
        public Disjoint(int n){
            this.size = new int[n + 1];
            this.parent = new int[n + 1];
            for(int i = 0 ; i <  n ; i++){
                this.size[i] = 1;
                this.parent[i] = i;
            }
        }

        int parent(int u){
            if(u == this.parent[u]){
                return u;
            }
            int p = parent(this.parent[u]);
            this.parent[u] = p;
            return p;
        }

        void union(int u , int v){
            int pu = parent(u);
            int pv = parent(v);
            if(pu == pv){
                return;
            }

            if(size[pv] > size[pu]){
                parent[pu] = pv;
                size[pv] = size[pv] + size[pu];
            }else{
                parent[pv] = pu;
                size[pu] = size[pu] + size[pv];
            }
        }
    }

    boolean valid(int r , int c , int n){
        return (r >=0 && r < n && c >= 0 && c < n);
    }
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        Disjoint ds = new Disjoint(n*n + 1);
        int max = 0;

        // connect all ones to their neighbours ones.
        for(int i = 0 ; i < n ;i ++){
            for(int j = 0; j < n ; j++){
                //if its a 1 .. simple merge with neigh island.
                int[] dr = {0,0,1,-1};
                int[] dc = {-1,1,0,0};
                if(grid[i][j] == 1){
                    for(int k = 0 ; k < 4; k++){
                        int r = i + dr[k];
                        int c = j + dc[k];
                        if(valid(r,c,n) && grid[r][c] == 1){
                            int nodeNo = i*n + j;
                            int adjNodeNo = r*n + c;
                            ds.union(nodeNo,adjNodeNo);
                        }
                    }
                }
            }
        }

        // This loop will not execute if all are ones.
        // Then the size of the max Island will be ds.size[ds.ultimateParent(0)].
        for(int i = 0 ; i < n ;i ++){
            for(int j = 0; j < n ; j++){
                int[] dr = {0,0,1,-1};
                int[] dc = {-1,1,0,0};
                if(grid[i][j] == 0){
                    int sum = 0;
                    //this set will contain all the ultimate parent of island to which we can connect.
                    HashSet<Integer> set = new HashSet<>();
                    for(int k = 0 ; k < 4; k++){
                        int r = i + dr[k];
                        int c = j + dc[k];
                        if(valid(r,c,n) && grid[r][c] == 1){  // i can connect to this island .. add it's ultimate parent to the set.
                            int adjNodeNo = r*n + c;
                            set.add(ds.parent(adjNodeNo));
                        }
                    }
                    for(int a : set){
                        sum = sum + ds.size[a];
                    }
                    max = Math.max(max , sum + 1);
                }
            }
        }


        return Math.max(max , ds.size[ds.parent(0)]);
    }
}
