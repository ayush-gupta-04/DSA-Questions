```
class Solution {
    static class DisjointSet{
        int[] parent;
        int[] size;
        public DisjointSet(int n){
            this.parent = new int[n + 1];
            this.size = new int[n + 1];
            for(int i = 0; i <= n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int u){
            if(u == parent[u]){
                return u;
            }

            int p =  findParent(parent[u]);
            parent[u] = p;
            return p;
        }

        void union(int u, int v){
            int pu = findParent(u);
            int pv = findParent(v);
            if(pu == pv) return;

            if(size[pu] > size[pv]){
                size[pu] += size[pv];
                parent[pv] = pu;
            }else{
                size[pv] += size[pu];
                parent[pu] = pv;
            }
        }

        boolean isConnected(int u , int v){
            return findParent(u) == findParent(v);
        }
    }
    public int minCostConnectPoints(int[][] points) {
        int n  = points.length;
        int sum = 0;
        DisjointSet ds = new DisjointSet(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x , y) -> Integer.compare(x[2] , y[2]));

        //put all possible edges to the pq.
        for(int i = 0 ;i < n; i++){
            for(int j = i + 1; j < n; j++){
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pq.offer(new int[]{i , j , dist});
            }
        }
        int edges = 0;
        while(!pq.isEmpty() && edges != n-1){
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            int dist = edge[2];
            if(!ds.isConnected(u , v)){
                ds.union(u , v);
                sum += dist;
            }
        }

        return sum;
    }
}
```
