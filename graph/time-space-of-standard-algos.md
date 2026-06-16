### Breadth-First Search (BFS)
##### undirected
- Time : V + 2*E
  - Every single vertex (V) is added to the queue and removed from the queue exactly once.
  - When a vertex is removed, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex once and every edge exactly twice."
  - This 2*E is for undirected.
- Space : 2*V
  - V for visited array.
  - V for queue in the worst case.


##### directed
- Time : V + E
  - Every single vertex (V) is added to the queue and removed from the queue exactly once.
  - When a vertex is removed, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex and every edge exactly once."
- Space : 2*V
  - V for visited array.
  - V for queue in the worst case.


 ### Depth-First Search (DFS)
##### undirected
- Time : V + 2*E
  - The algorithm visits every vertex V exactly once.
  - For every vertex, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex once and every edge exactly twice."
  - This 2*E is for undirected.
- Space : 2*V
  - V for visited array.
  - V for recursive stack in the worst case.


##### directed
- Time : V + 2*E
  - The algorithm visits every vertex V exactly once.
  - For every vertex, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex and every edge exactly once."
- Space : 2*V
  - V for visited array.
  - V for recursive stack in the worst case.


### Topological Sort
##### (Kahn's Algorithm -- BFS)
- Time : V + E
  - same as BFS
- Space : 2*V
  - same as BFS

##### (DFS)
- Time : V + E
  - same as DFS
- Space : 2*V
  - same as DFS
