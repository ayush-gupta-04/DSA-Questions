/*
 * CORE INTUITION & THOUGHT PROCESS (LeetCode 2127)
 * ==============================================================
 *
 * 1. THE GRAPH STRUCTURE
 *    - Each employee points to exactly 1 person (out-degree = 1).
 *    - In graph theory, this is a "functional directed graph".
 *    - Every component is shaped like trees/branches feeding into a single cycle.
 *
 * 2. TABLE SEATING RULES
 *    - A round table means each person has exactly TWO adjacent neighbors.
 *    - To attend, person i MUST have favorite[i] sitting on one side.
 *    - Based on cycle size (k), only TWO seating arrangements are valid:
 *
 * --------------------------------------------------------------
 * CASE 1: Cycle of length k >= 3 (Closed Ring)
 * --------------------------------------------------------------
 *    - Example: 0 -> 1 -> 2 -> 0
 *    - Seated as: (0) - (1) - (2) - (0)
 *    - Notice: Every person in this cycle already has BOTH neighbor seats 
 *      occupied by cycle members.
 *    - Consequence:
 *      * No branch node can sit next to them.
 *      * Two separate cycles cannot merge at the same table.
 *    - Result for Case 1:
 *      * Best we can do is take the LARGEST single cycle of size >= 3.
 *
 * --------------------------------------------------------------
 * CASE 2: Mutual Pairs (k = 2) with Inbound Chains (Open Ring)
 * --------------------------------------------------------------
 *    - Example: A likes B, and B likes A (A <==> B).
 *    - Because A and B satisfy each other by sitting together, each has 
 *      ONE FREE SEAT on their other side!
 *    - We can attach the longest incoming chain to A, and the longest to B:
 *        [... -> c2 -> c1 -> A] <==> [B <- d1 <- d2 <- ...]
 *    - Notice: The ends of these chains do not care who sits on their outer side.
 *    - Consequence:
 *      * We can string MULTIPLE 2-cycle blocks together on the same table!
 *    - Result for Case 2:
 *      * SUM of (2 + longest_chain(A) + longest_chain(B)) for ALL mutual pairs.
 *
 * --------------------------------------------------------------
 * 3. ALGORITHM WALKTHROUGH
 * --------------------------------------------------------------
 *    Step 1: Kahn's Algorithm (Topological Sort)
 *            - Trim leaf nodes (in-degree == 0) layer-by-layer.
 *            - Track depth[u]: the longest branch length leading into node u.
 *            - When done, all remaining nodes with in-degree > 0 belong to cycles.
 *
 *    Step 2: Traverse Cycles
 *            - If cycle length >= 3:
 *                maxCycle = max(maxCycle, cycleLength)
 *            - If cycle length == 2 (mutual pair u, v):
 *                sumPairs += (2 + depth[u] + depth[v])
 *
 *    Step 3: Answer = max(maxCycle, sumPairs)
 */




import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        
        for (int fav : favorite) {
            inDegree[fav]++;
        }
        
        // depth[i] stores the length of the longest chain ending at node i
        int[] depth = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        
        // Push all leaf nodes (not part of any cycle)
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Step 1: Process tree branches using Kahn's algorithm
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int next = favorite[curr];
            
            depth[next] = Math.max(depth[next], depth[curr] + 1);
            
            if (--inDegree[next] == 0) {
                queue.offer(next);
            }
        }
        
        // Step 2: Detect and evaluate cycles
        int maxCycleLen = 0;
        int sum2CycleChains = 0;
        
        for (int i = 0; i < n; i++) {
            // Nodes with inDegree > 0 are part of a cycle
            if (inDegree[i] > 0) {
                int cycleLen = 0;
                int curr = i;
                
                // Traverse the cycle
                while (inDegree[curr] > 0) {
                    inDegree[curr] = 0; // Mark as visited
                    cycleLen++;
                    curr = favorite[curr];
                }
                
                if (cycleLen == 2) {
                    // Mutual pair: u <-> v
                    int u = i;
                    int v = favorite[i];
                    sum2CycleChains += 2 + depth[u] + depth[v];
                } else if (cycleLen > 2) {
                    // Cycle of length >= 3
                    maxCycleLen = Math.max(maxCycleLen, cycleLen);
                }
            }
        }
        
        return Math.max(maxCycleLen, sum2CycleChains);
    }
}
