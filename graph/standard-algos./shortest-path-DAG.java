// time : 2*(V + E)  standard BFS + edge relaxation.
// - one for topo sort.
// - one for finding dist of neigh.
// space : 3V
// - V : inDegree.
// - V : queue.
// - V : dist

// Intution : 
// - 0 -> 2
// - 1 -> 2
// - To find the shortest dist of 2 .. it would be best if we know the shotest dist of 0 and 1 first.
// - If we process nodes in TOPO order .. it would be best.


// Algo : 
// find topo order.
// take out nodes in topo order.
// relax their neigh.

// Pitfalls : 
// - This algorithm only works if the graph is a DAG.
// - A node could be unreachable from source dist will be Integer.MAX_VALUE. 
// - Always verify that distance[curr] != Integer.MAX_VALUE before relaxing its neighbors to avoid integer overflow.
public class ShortestPathDAG {
    static class Edge {
        int target;
        int weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static int[] findShortestPathInDAG(List<List<Edge>> graph, int source, int V) {
        // Step 1: Compute In-Degrees of all nodes
        int[] inDegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (Edge edge : graph.get(u)) {
                inDegree[edge.target]++;
            }
        }

        // Step 2: Kahn's Algorithm (BFS) to find Topological Order
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.pollFirst();
            topoOrder.add(curr);

            for (Edge edge : graph.get(curr)) {
                inDegree[edge.target]--;
                if (inDegree[edge.target] == 0) {
                    queue.offerLast(edge.target);
                }
            }
        }

        // Step 3: Initialize distances array with Infinity
        int INF = 1_000_000_000;
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        distance[source] = 0; // Distance to self is 0

        // Step 4: Process nodes in Topological Order
        for (int curr : topoOrder) {
            // Process neighbors only if the current node is reachable from the source
            if(distance[curr] == INF) continue;
            
            for (Edge edge : graph.get(curr)) {
                if (distance[curr] + edge.weight < distance[edge.target]) {
                    distance[edge.target] = distance[curr] + edge.weight;
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Graph Construction
        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(4, 1));
        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(3, 6));
        graph.get(4).add(new Edge(2, 2));
        graph.get(4).add(new Edge(5, 4));
        graph.get(5).add(new Edge(3, 1));

        int source = 0;
        int[] distances = findShortestPathInDAG(graph, source, V);

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < V; i++) {
            String distStr = (distances[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(distances[i]);
            System.out.println("To node " + i + " -> " + distStr);
        }
    }
}
