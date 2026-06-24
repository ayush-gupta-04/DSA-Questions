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
