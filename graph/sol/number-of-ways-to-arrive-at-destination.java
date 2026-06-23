class Solution {
    class Pair{
        int node;
        long dist;
        public Pair(long dist , int node){
            this.dist = dist;
            this.node = node;
        }
    }
    List<List<int[]>> fillAdj(int[][] edges,int n){
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new int[]{v,wt});
            adj.get(v).add(new int[]{u,wt});
        }

        return adj;
    }
    public int countPaths(int n, int[][] edges) {
        List<List<int[]>> adj = fillAdj(edges,n);
        long[] distance = new long[n];
        Arrays.fill(distance,Long.MAX_VALUE);
        int[] ways = new int[n];
        int mod = (int)(1e9 + 7);
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(Comparator.comparingLong(a -> a.dist));


        distance[0] = 0;
        ways[0] = 1;
        pq.offer(new Pair(0,0));

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int node = p.node;
            long dist = p.dist;

            for(int[] neigh : adj.get(node)){
                int v = neigh[0];
                int wt = neigh[1];
                if(dist + wt < distance[v] ){
                    distance[v] = dist + wt;
                    ways[v] = ways[node];     // the number of ways to reach v will be same as number of ways to reach it's parent.
                    pq.offer(new Pair(distance[u],u));
                }else if(dist + wt == distance[u]){   // we explored new ways to reach node u via node "node"
                    ways[u] = (ways[node] + ways[u])%mod;
                }
            }
        }

        return ways[n-1];
    }
}
