
import java.util.*;

public class LinkedInNetwork {

    private final Map<String, Set<String>> graph = new HashMap<>();

    // Connections are mutual, so the graph is undirected.
    public void addConnection(String user1, String user2) {
        graph.computeIfAbsent(user1, k -> new LinkedHashSet<>())
            .add(user2);
        graph.computeIfAbsent(user2, k -> new LinkedHashSet<>())
            .add(user1);
    }

    public Map<Integer, Set<String>> findConnections(String source, int maxDegree) {
      
        if (maxDegree < 1) {
            throw new IllegalArgumentException(
                "maxDegree must be at least 1");
        }

        Map<Integer, Set<String>> result = new HashMap<>();

        for (int degree = 1; degree <= maxDegree; degree++) {
            result.put(degree, new HashMap<>());
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        int degree = 0;

        while (!queue.isEmpty() && degree < maxDegree) {
            int levelSize = queue.size();
            degree++;
            // Process exactly one BFS level.
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                for (String neighbor : graph.getOrDefault(current, Collections.emptySet())) {
                    if(visited.contains(neighbour)) continue;
                    result.get(degree).add(neighbor);
                    queue.offer(neighbor);
                    visited.add(neighbour);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedInNetwork network = new LinkedInNetwork();
        network.addConnection("A", "B");
        network.addConnection("A", "C");
        network.addConnection("B", "D");
        network.addConnection("B", "E");
        network.addConnection("C", "E");
        network.addConnection("C", "F");
        network.addConnection("D", "G");
        network.addConnection("E", "H");
        network.addConnection("F", "I");
        System.out.println(network.findConnections("A", 3));
    }
}
