/*
 * ============================================================================
 * INTUITION: Two-Level Topological Sort (Macro & Micro Ordering)
 * ============================================================================
 * The problem requires two distinct constraints to be satisfied simultaneously:
 *  1. Micro-level: Item-to-item dependencies (u must appear before v).
 *  2. Macro-level: Group cohesion (all items in group G must appear contiguously).
 *
 * If items in the same group must stay together, then groups themselves have 
 * dependencies. For instance, if item u (in group A) must come before item v 
 * (in group B), the entire group A must be scheduled before group B.
 *
 * To make this uniform:
 *  - Treat "ungrouped" items (-1) as individual single-member groups.
 *  - Decompose the problem into two separate Directed Acyclic Graphs (DAGs):
 *      1. Group Graph: Orders the groups relative to each other.
 *      2. Item Graph:  Orders items relative to each other.
 *
 * If neither graph has a cycle, we can simply place the ordered items into 
 * their respective group buckets and output group by group.
 * ============================================================================
 * APPROACH:
 * ============================================================================
 * 1. Normalize Groups:
 *    - Assign each item with group[i] == -1 its own unique group ID starting 
 *      from m, m + 1, ... so every element belongs to an isolated group.
 *
 * 2. Build Dual Dependency Graphs:
 *    - Item Graph:  u -> v for every dependency u in beforeItems[v].
 *    - Group Graph: group[u] -> group[v] whenever u -> v and group[u] != group[v].
 *    - Track in-degrees for both graphs.
 *
 * 3. Topological Sort (Kahn's Algorithm):
 *    - Run topological sort on the Item Graph (gives globally valid item order).
 *    - Run topological sort on the Group Graph (gives valid group order).
 *    - Cycle check: If sortedItems.size() < n or sortedGroups.size() < totalGroups, 
 *      a dependency conflict exists; return [].
 *
 * 4. Combine / Bucket Placement:
 *    - Bucket the topologically sorted items by their group ID. Since the items 
 *      are inserted in topological order, their relative intra-group dependencies 
 *      are preserved automatically.
 *    - Traverse the sorted groups in order, appending each group's bucket of 
 *      items into the final result array.
 *
 * Complexity:
 *  - Time Complexity:  O(V + E) = O(n + m + sum(beforeItems.length))
 *  - Space Complexity: O(V + E) for graphs, in-degrees, and queue structures.
 * ============================================================================
 */



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

        int[] arr = new int[resultList.size()];
        int k = 0;
        for(int a : resultList) arr[k++] = a;

        return arr;
    }

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
