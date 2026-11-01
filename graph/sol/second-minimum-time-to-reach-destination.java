// ------------- Dijkistra Solution -----------------

// Intuition : 
// - It is easy to find the minimum solution using Dijkistra's.
// - We could have dist[node][2] .. first slot for minimum, 2nd slot for 2nd minimum.

class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int INF = 1_000_000_000;
        int[][] dist = new int[n+1][2];
        for(int[] d : dist) Arrays.fill(d, INF);

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));

        pq.offer(new int[]{1, 0});
        dist[1][0] = 0;

        while(!pq.isEmpty()){
            int node = pq.peek()[0];
            int cost = pq.peek()[1];
            pq.poll();

            boolean red = false;
            if((cost/change)%2==1) red = true;

            for(int next : adj.get(node)){

                int nextCost = red ? (cost/change)*change + change + time : cost+time;

                if(nextCost < dist[next][0]){
                    dist[next][1] = dist[next][0];
                    dist[next][0] = nextCost;
                    pq.offer(new int[]{next, dist[next][0]});
                }else if(nextCost > dist[next][0] && nextCost < dist[next][1]){
                    dist[next][1] = nextCost;
                    pq.offer(new int[]{next, dist[next][1]});
                }
            }
        }

        return dist[n][1];
    }
}
