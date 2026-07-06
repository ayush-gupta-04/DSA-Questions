// ------------------ State Expansion Dijkistra's ---------------------
// Understanding the Problem : 
// - We have to go to the dest in min time and max remaining power.
// - route 1 : takes 2 hr but remPow will be 10%.
// - route 2 : takes 5 hr but remPow will be 50%.
// - a simple standard Dijkistra would only consider route 1 .. stucking in the end.

// Some Imp Steps : 
// - I have to minimize time but but but .. remPow is stopping me.
// - This is Dijkistra with Constrainst.
// - My state must include remPow also.
// - If i am at a node .. i should have these infos : so i can take a decision.
//     - the node.
//     - time taken.
//     - rem pow.
// - my PQ should have {node , time , remPow} ... sorted by ... time ASC then remPow DESC.
// - Reaching a node with remPow = 20 is very much different from reaching the same node with remPow = 30.
//       because it opens up a full new possibilities.
// - We must expand the state from time[nodes] --to-> time[nodes][remPow].
// - time[node][remPow] : what is the minimum time to reach node with remaining power = remPow.

// why we need times[][] ... 2D stuff.
// - I have visited a node with time = 10 and remPow = 5
// - opt 1 ... 1D : if i visit with time = 5 and remPow = 5 .. it will allow to update.
// - opt 2 ... 1D : if i visit with time = 12 and remPow = 8 .. it will not allow to update .. ideally it should allow this.
// The problem is because we have merged everything.
// We should have times[node][remPow].

// - I have visited a node with time = 10 and remPow = 5
// - opt 1 ... 2D : if i visit with time = 5 and remPow = 5 .. it will allow to update.
// - opt 2 ... 2D : if i visit with time = 12 and remPow = 8 .. it will allow to update .. having 2D allowed this.

// Crux : 
// - I should know {node , time, remPow}.
// - If i only see the min time ... then my standard dijkistra will ignore a path with more time & more remPow.
// - Only taking times[node] is flaw.
// - Take times[node][remPow] : what is the minimum time to reach node with remaining power = remPow.



class Solution {
    static class Data{
        int remPow;
        long time;
        int node;
        public Data(int p, long t, int u){
            this.remPow = p;
            this.time = t;
            this.node = u;
        }
    }
    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int src, int tar) {
        if(src == tar){
            return new long[]{0L,(long)power};
        }
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0;i < n;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int t = e[2];
            adj.get(u).add(new int[]{v,t});
        }
        long INF = 1_000_000_000_000_000L;
        long[][] times = new long[n][power + 1];
        for(long[] a : times) Arrays.fill(a , INF);
        PriorityQueue<Data> pq = new PriorityQueue<>((x,y) -> {
            if(x.time != y.time) return Long.compare(x.time,y.time);
            return Integer.compare(y.remPow , x.remPow);
        });

        pq.offer(new Data(power,0,src));
        times[src][power] = 0L;
        int maxPow = 0;

        while(!pq.isEmpty()){
            Data d = pq.poll();
            int remPower = d.remPow;
            long time = d.time;
            int node = d.node;

            if(node == tar){
                return new long[]{time , remPower};
            }

            if(remPower < cost[node]) continue;

            for(int[] neigh : adj.get(node)){
                int v = neigh[0];
                int t = neigh[1];
                int newPow = remPower-cost[node];
                if(time + t < times[v][newPow]){
                    times[v][newPow] = time + t;
                    pq.offer(new Data(remPower-cost[node] , times[v][newPow] , v));
                }
            }
        }
        return new long[]{-1L,-1L};
    }
}



