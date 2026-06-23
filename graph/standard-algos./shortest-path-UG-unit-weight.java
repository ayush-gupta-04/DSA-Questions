// time : BFS standard
// space : BFS standard

// The first time we visit a node .. it's with the shortest path.
import java.util.*;

public class ShortestPathBFS {

    //  main function for shortest path.
    public static int[] findShortestPath(List<List<Integer>> graph, int source, int V) {
        int[] distance = new int[V];
        Arrays.fill(distance, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        distance[source] = 0;
        queue.offerLast(source);
        
        while (!queue.isEmpty()) {
            int currNode = queue.pollFirst();
            
            for (int neighbor : graph.get(currNode)) {
                if (distance[neighbor] == -1) {  // If distance is -1, it means this node is UNVISITED
                    distance[neighbor] = distance[currNode] + 1;
                    queue.offerLast(neighbor);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1); graph.get(0).add(2);
        graph.get(1).add(0); graph.get(1).add(3); graph.get(1).add(2);
        graph.get(2).add(0); graph.get(2).add(1); graph.get(2).add(4);
        graph.get(3).add(1); graph.get(3).add(4);
        graph.get(4).add(2); graph.get(4).add(3);

        int sourceNode = 0;
        findShortestPath(graph, sourceNode, V);
    }
}
