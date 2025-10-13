```
import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        // Step 1: Assign a new, unique group ID to each item in group -1.
        // This treats each of them as a separate group.
        int groupCount = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupCount++;
            }
        }

        // Step 2: Build the dependency graphs for both items and groups.
        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];
        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupIndegree = new int[groupCount];

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < groupCount; i++) {
            groupGraph.add(new ArrayList<>());
        }

        for (int v = 0; v < n; v++) {
            for (int u : beforeItems.get(v)) {
                // Item dependency: u -> v
                itemGraph.get(u).add(v);
                itemIndegree[v]++;

                // If items are in different groups, add a group dependency.
                if (group[u] != group[v]) {
                    groupGraph.get(group[u]).add(group[v]);
                    groupIndegree[group[v]]++;
                }
            }
        }

        // Step 3: Perform topological sort on items and groups separately.
        List<Integer> sortedItems = topologicalSort(itemGraph, itemIndegree);
        List<Integer> sortedGroups = topologicalSort(groupGraph, groupIndegree);

        // If either sort failed (due to a cycle), a solution is impossible.
        if (sortedItems.size() < n || sortedGroups.size() < groupCount) {
            return new int[0];
        }

        // Step 4: Combine the sorted results.
        // Place the globally sorted items into buckets based on their group.
        // The relative order of items within each bucket is preserved from the item sort.
        Map<Integer, List<Integer>> groupToOrderedItems = new HashMap<>();
        for (int item : sortedItems) {
            groupToOrderedItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Construct the final result array by appending the item lists
        // in the order determined by the group sort.
        List<Integer> resultList = new ArrayList<>();
        for (int groupId : sortedGroups) {
            resultList.addAll(groupToOrderedItems.getOrDefault(groupId, new ArrayList<>()));
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Performs a topological sort (Kahn's algorithm) on a given graph.
     * @param graph The adjacency list of the graph.
     * @param indegree The in-degree of each node.
     * @return A list of sorted nodes. If a cycle is detected, the list size
     * will be smaller than the number of nodes.
     */
    private List<Integer> topologicalSort(List<List<Integer>> graph, int[] indegree) {
        List<Integer> sortedList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Initialize the queue with all nodes having an in-degree of 0.
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            sortedList.add(u);
            
            for (int v : graph.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        
        return sortedList;
    }
}

```
