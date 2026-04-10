
// The Master Approach (The "Aha!" Moment) 💡
// Shift your perspective: Instead of walking from stop to stop, we need to walk from bus to bus.
// Graph Modeling:
// Nodes: The Buses (Routes).
// Edges: Two buses share an edge if they share at least one common bus stop (meaning you can transfer between them).

// The Plan: 1. We map every bus stop to a list of buses that visit it.
// 2. We start our BFS at the source stop.
// 3. We look at all buses we can catch from our current stop.
// 4. For each bus, we ride it to all of its stops.
// 5. From those new stops, we catch new buses, and so on, level by level, until we hit the target stop.

import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // Edge Case: We are already at the target destination
        if (source == target) {
            return 0;
        }

        // Step 1: Build the graph mapping each stop to the buses (route indices) that visit it.
        // Key: Bus Stop ID | Value: List of Bus IDs (indices in the routes array)
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        
        for (int busId = 0; busId < routes.length; busId++) {
            for (int stop : routes[busId]) {
                stopToBuses.putIfAbsent(stop, new ArrayList<>());
                stopToBuses.get(stop).add(busId);
            }
        }

        // Step 2: Initialize BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        
        // Keep track of which buses we have already ridden to avoid infinite loops
        boolean[] visitedBuses = new boolean[routes.length];
        
        int busesTaken = 0;

        // Step 3: Run BFS Level by Level
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all stops we can reach with the current number of buses
            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();
                
                // If we've reached our destination, return the count
                if (currentStop == target) {
                    return busesTaken;
                }
                
                // Get all buses that pass through the current stop
                List<Integer> busesAtCurrentStop = stopToBuses.getOrDefault(currentStop, new ArrayList<>());
                
                for (int busId : busesAtCurrentStop) {
                    // If we haven't taken this bus yet
                    if (!visitedBuses[busId]) {
                        visitedBuses[busId] = true; // Mark as taken
                        
                        // Add all stops on this bus route to our queue for the next level
                        for (int nextStop : routes[busId]) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
            // After processing a full level of transfers, increment the bus count
            busesTaken++;
        }

        // If the queue empties and we never found the target, it's unreachable
        return -1;
    }
}
