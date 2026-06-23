// time : E
// space : E + V

// If we apply dijkistras using PQ : 
//   - we would end up choosing the smallest path to dest but will voilate the K stops criteria.

// - We first satisfy the K stops criteria.
// - We must use a modified version of Dijkistra.
// - we will use queue instead of a PQ .. it will make sure we only go upto K stops.


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
        //creating the adj list.
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : flights){
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj.get(u).add(new int[]{v,cost});
        }


        //initialisation.
        int INF = 1_000_000_000;
        int[] costs = new int[n];
        Arrays.fill(costs,INF);
        Deque<int[]> q = new ArrayDeque<>();


        costs[src] = 0;
        q.offerLast(new int[]{src , 0,0});  // source , cost , stops

        while(!q.isEmpty()){
            int node = q.peekFirst()[0];
            int cost = q.peekFirst()[1];
            int stops = q.peekFirst()[2];
            q.pollFirst();

            if(stops > k) continue;

            for(int[] neigh : adj.get(node)){
                int v = neigh[0];
                int wt = neigh[1];
                if(cost + wt < costs[v]){
                    costs[v] = cost + wt;
                    q.offerLast(new int[]{v , costs[v], stops + 1});
                }
            }
        }
        return costs[dest] == INF ? -1 : costs[dest];
    }
}
