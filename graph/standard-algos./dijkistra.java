// ------------- best case ----------------
// The graph is sparse.

// time : (V + E)*logV 
// - Extracting the minimum element from the PQ takes logV , done V times ... so V*logV
// - Inserting a relaxed path state into the PQ takes logV , done E times ... so E*logV
// space : V + E
// - V : distance[], vis[].
// - E : size of PQ .. worst case it will have all the edges.



// ---------- worst case --------------
// The graph is dense, E = V²

// time : (V²)*logV 
// - Extracting the minimum element from the PQ takes logV , done V times ... so V*logV
// - Inserting a relaxed path state into the PQ takes logV , done E times ... so (V²)*logV
// space : V + V²
// - V : distance[], vis[].
// - V² : size of PQ .. worst case it will have all the edges.


// Common Pitfalls & Exceptions
// - The Negative Edge Failure : 
//     - Dijkstra's algorithm is greedy and assumes that once a node is popped from the priority queue, its shortest path has been definitively found.
//     - If graph has -ve weights .. There will always be a shorter path for a node.
//     - Dijkistra will run forever.


public class StandardDijkstra {
    static class Pair {
        int node;
        int distance;

        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // main logic for dijkistras.
    public static int[] dijkstra(List<List<Pair>> graph, int source, int V) {
        int INF = 1_000_000_00;
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));

        distance[source] = 0;
        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int dist = curr.distance;

            // If we have already finalized the shortest path for this node .. skip it.
            if (vis[node]) continue;
            vis[node] = true;

            for (Pair neigh : graph.get(node)) {
                int v = neigh.node;
                int wt = neigh.distance;

                if (dist + wt < distance[v]) {
                    distance[v] = dist + wt;
                    pq.offer(new Pair(v, distance[v]));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Pair(1, 1));
        graph.get(0).add(new Pair(2, 4));
        graph.get(1).add(new Pair(3, 3));
        graph.get(2).add(new Pair(3, 1));

        int source = 0;
        int[] distances = dijkstra(graph, source, V);
    }
}
