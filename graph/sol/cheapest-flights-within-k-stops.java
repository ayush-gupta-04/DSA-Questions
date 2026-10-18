// time : E
// space : E + V

// If we apply dijkistras using PQ : 
//   - we would end up choosing the smallest path to dest but will voilate the K stops criteria.
//      
// src ----A------> inter    .. cost 10 , stops 2
// src ----B------> inter    .. cost 6 , stops 5
// 
// - Dijkistra would conisder path B first .. and it may violate the K stops criteria. It won't allow path A to be explored then.
// - If we would have conidered path A first, then K stops criteria would be satisfied.


// - We first satisfy the K stops criteria.
// - We must use a modified version of Dijkistra.
// - we will use queue instead of a PQ .. will explore level by level .. it will make sure we only go upto K stops.
// - we don't use a vis[] because we can explore same node twice with smaller distances.


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






// ------------------ Optimal Solution -----------------
// The core intuition behind using Bellman-Ford for this problem comes down to one defining property of the algorithm: 
// each outer loop iteration calculates the shortest path using exactly one additional edge.

// Here is how that perfectly maps to the "Cheapest Flights" problem:

// 1. Edges Equal Flights
//     In graph terms, a direct flight between two cities is 1 edge. A journey with 1 stop uses 2 flights (2 edges). 
//     A journey with k stops uses exactly k + 1 flights. Because the problem restricts us to a maximum of k stops, we only care about paths that are at most k + 1 edges long.

// 2. The Step-by-Step Expansion
//     Instead of blindly searching the whole graph, Bellman-Ford expands outwards in controlled waves:
//         Iteration 1: It finds the cheapest price to all cities reachable by 1 direct flight (0 stops).
//         Iteration 2: It takes the prices from Iteration 1 and checks if it can reach new cities by adding 1 more flight (1 stop).
//         Iteration k + 1: It finds the cheapest prices using at most k + 1 flights (k stops).
//     Once the loop runs k + 1 times, you automatically have your answer without needing to keep track of the stop count in a queue.

// 3. Why the temp Array is the Secret Sauce
//     If you update the main costs array immediately during the loop, you might accidentally chain flights together too quickly. 
//     For example, if the loop processes A -> B and then immediately processes B -> C, you just traveled 2 edges in a single iteration. This breaks the stop limit.
//     By calculating the new costs based on the previous iteration's array (costs) and saving them to a new array (temp), 
//     you ensure that every city only takes exactly one step forward per loop.



class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
        int[] costs = new int[n];
        Arrays.fill(costs, 1_000_000_000);
        costs[src] = 0;

        // Run exactly k + 1 iterations
        for (int i = 0; i <= k; i++) {
            // Use a temporary array to prevent using updated costs from the same step
            int[] temp = Arrays.copyOf(costs, n);
            
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];
                
                // If the source node has been reached, check if we can find a cheaper path
                if (costs[u] != 1_000_000_000 && costs[u] + price < temp[v]) {
                    temp[v] = costs[u] + price;
                }
            }
            costs = temp;
        }

        return costs[dest] == 1_000_000_000 ? -1 : costs[dest];
    }
}
