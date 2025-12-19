# Hierholzer’s Algorithm — Complete Revision Note

---

## 1. What problem does Hierholzer’s Algorithm solve?

Hierholzer’s Algorithm finds a **path that uses every edge of a graph exactly once**.

* If the path **starts and ends at the same node** → **Eulerian Circuit**
* If the path **starts and ends at different nodes** → **Eulerian Path**

In many problems (including LeetCode 332), the existence of such a path is **guaranteed**, so we only need to construct it.

---

## 2. Core idea (intuition first)

Think of:

* each edge as a **one-time-use ticket**
* once used, it disappears
* nodes may be revisited; edges may not

**Key insight:**

> A node should be added to the final answer **only after all its outgoing edges have been used**.

This means:

* we walk forward greedily
* we remove edges as we go
* when we get stuck, we record the node
* the path is built **in reverse**

---

## 3. Algorithm (Hierholzer’s Algorithm)

### Steps

1. Build a graph using an adjacency list
2. Start DFS from the given start node
3. While the current node has outgoing edges:

   * pick one edge
   * remove it
   * DFS to the next node
4. When no outgoing edges remain:

   * add the current node to the answer
5. Reverse the answer to get the correct order

### Why it works

* Every edge is visited exactly once
* Dead ends naturally appear at the end of the path
* Backtracking stitches cycles together automatically
* No backtracking over choices → linear complexity

---

## 4. Applying Hierholzer to **Reconstruct Itinerary (LeetCode 332)**

### Problem constraints recap

* Each ticket must be used exactly once
* Start from `"JFK"`
* If multiple itineraries exist, return the **lexicographically smallest** one

### Mapping to graph theory

| Problem element       | Graph interpretation        |
| --------------------- | --------------------------- |
| Airport               | Node                        |
| Ticket                | Directed edge               |
| Use every ticket once | Eulerian path               |
| Lexicographical order | Always choose smallest edge |

---

## 5. Handling lexicographical order

When multiple outgoing edges exist:

* we must always choose the **smallest destination**

Solution:

* store neighbors in a **min-heap (priority queue)**

```cpp
priority_queue<string, vector<string>, greater<string>>
```

This preserves correctness because:

* Eulerian paths do not depend on which edge is chosen first
* all edges are guaranteed to be used eventually

---

## 6. Final algorithm for this problem

1. Build a directed graph:

   ```cpp
   from → min-heap of destinations
   ```
2. Run Hierholzer DFS from `"JFK"`
3. Add nodes to the result in postorder
4. Reverse the result

---

## 7. Time and space complexity

* **Time:**
  `O(E log E)`
  (each edge pushed and popped once from a heap)

* **Space:**
  `O(E + V)`
  (graph + recursion stack)

---

## 8. Final C++ Implementation (Reference Code)

```cpp
class Solution {
public:
    unordered_map<string,
        priority_queue<string, vector<string>, greater<string>>> graph;

    vector<string> itinerary;

    void dfs(const string& u) {
        // Consume all outgoing edges
        while (!graph[u].empty()) {
            string v = graph[u].top();
            graph[u].pop();
            dfs(v);
        }
        // Add node after all edges are used
        itinerary.push_back(u);
    }

    vector<string> findItinerary(vector<vector<string>>& tickets) {
        // Build graph
        for (auto& t : tickets) {
            graph[t[0]].push(t[1]);
        }

        // Apply Hierholzer’s Algorithm
        dfs("JFK");

        // Reverse to get correct order
        reverse(itinerary.begin(), itinerary.end());
        return itinerary;
    }
};
```

---

## 9. One-line memory hook (very important)

> **“Use edges greedily, record nodes only when stuck.”**

If you remember this line, you remember Hierholzer’s Algorithm.

---

## 10. When to recognize Hierholzer instantly

Use this algorithm when:

* every edge must be used exactly once
* revisiting nodes is allowed
* the path or order matters

If those conditions appear together, **do not backtrack** — think Eulerian.
