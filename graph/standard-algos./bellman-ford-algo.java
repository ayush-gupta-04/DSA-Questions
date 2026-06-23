// time : V*E
// space : V


// Used when : 
// - Finds dist of src to all other nodes.
// - Used to detect a -ve cycle .. if exist.

// Intuition for Bellman-Ford
// - It proves that we need at most V-1 times to relax every edge.
// - If we are able to relax Vth time .. there is absolutely a cycle.
//   - a simple dijkistra would fail .. it will run infinitely relaxing forever if -ve cycle present.



import java.util.*;

public class BellmanFord {

    static class Edge {
        int src, dest, wt;
        public Edge(int s, int d, int w) {
            this.src = s; this.dest = d; this.wt = w;
        }
    }

    public static int[] findShortestPaths(int V, int startNode, List<Edge> edges) {
        int INF = 1_000_000_00;
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        distance[startNode] = 0;

        // Step 2: Relax all edges exactly (V - 1) times
        for (int i = 0; i < V-1; i++) {
            for (Edge edge : edges) {
                int u = edge.src;
                int v = edge.dest;
                int wt = edge.wt;

                if(distance[u] == INF) continue;  // if u is not reached yet .. skip relaxing it's neigh.

                if (distance[u] + wt < distance[v]) {
                    distance[v] = distance[u] + wt;
                }
            }
        }

        // If we are able to relax Vth time .. there is absolutely a cycle.
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.wt;

            if(distance[u] == INF) continue; // if u is not reached yet .. skip relaxing it's neigh.

            if (distance[u] + weight < distance[v]) {
                System.out.println("FATAL ERROR: Graph contains a Negative Weight Cycle! No shortest path exists.");
                return new int[]{}; 
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int V = 4; // 4 Nodes: 0, 1, 2, 3
        List<Edge> roads = new ArrayList<>();

        roads.add(new Edge(0, 1, 4));
        roads.add(new Edge(0, 2, 5));
        roads.add(new Edge(2, 1, -2)); // The negative shortcut!
        roads.add(new Edge(1, 3, 3));

        findShortestPaths(V, 0, roads);
    }
}

