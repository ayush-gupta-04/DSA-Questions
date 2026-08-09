## Breadth-First Search (BFS)
#### undirected
- Time : V + 2*E
  - Every single vertex (V) is added to the queue and removed from the queue exactly once.
  - When a vertex is removed, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex once and every edge exactly twice."
  - This 2*E is for undirected.
- Space : 2*V
  - V for visited array.
  - V for queue in the worst case.


#### directed
- Time : V + E
  - Every single vertex (V) is added to the queue and removed from the queue exactly once.
  - When a vertex is removed, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex and every edge exactly once."
- Space : 2*V
  - V for visited array.
  - V for queue in the worst case.


## Depth-First Search (DFS)
#### undirected
- Time : V + 2*E
  - The algorithm visits every vertex V exactly once.
  - For every vertex, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex once and every edge exactly twice."
  - This 2*E is for undirected.
- Space : 2*V
  - V for visited array.
  - V for recursive stack in the worst case.


#### directed
- Time : V + 2*E
  - The algorithm visits every vertex V exactly once.
  - For every vertex, we loop through all of its outgoing edges (E) to find unvisited neighbors.
  - It Means "we touch every vertex and every edge exactly once."
- Space : 2*V
  - V for visited array.
  - V for recursive stack in the worst case.


## Topological Sort
#### (Kahn's Algorithm -- BFS)
- Time : V + E
  - same as BFS
- Space : 2*V
  - same as BFS

#### (DFS)
- Time : V + E
  - same as DFS
- Space : 2*V
  - same as DFS


## Shortest Path
#### Un-Directed Graph Unit-Weight -- BFS
- time : V + E
    - Standard BFS
- Space : 2*V
    - Standard BFS


#### DAG
- time : 2*(V + E)  . . . . standard BFS + edge relaxation.
  - one for topo sort.
  - one for finding dist of neigh.
- space : 3V
   - V : inDegree.
   - V : queue.
   - V : dist
 
#### Dijkistra's Algorithm
------------- best case ----------------
- The graph is sparse. E ~ V
- heap operation : log(heap-size) = log(E) = log(V)

- Standard time : (vertex + edges)*log(heap-size)
- time : (V + E)*logV
  - Extracting the minimum element from the PQ takes log(heap-size) , done V times ... so V*log(heap-size)  .. so V*log(V)
  - Inserting a relaxed path state into the PQ takes log(heap-size) , done E times ... so E*log(heap-size)  .. so E*log(V)
- space : V + E
  - V : distance[], vis[].
  - E : size of PQ .. worst case it will have all the edges.



---------- worst case --------------
- The graph is dense, E = V²
- heap operation : log(heap-size) = log(E) = log(V²) = 2*log(V) = O(log(V))

- Standard time : (vertex + edges)*log(heap-size)
- time : (E)*log(V)   ... where E = V²
  - Extracting the minimum element from the PQ takes log(heap-size) , done V times ... so V*log(heap-size)  .. so V*log(V)
  - Inserting a relaxed path state into the PQ takes log(heap-size) , done E times ... so (E)*log(heap-size)  .. so E*log(V)
  - (E + V)log(V) = (E)log(V) .. since E will dominate here.
- space : V + E
  - V : distance[], vis[].
  - E : size of PQ .. worst case it will have all the edges.





#### Bellman Ford Algorithm
- time : V*E
- space : V


#### Floyd Warshall Algo
- time : V³
- space : V²


## MST (Minimum Spanning Tree)
#### Prim's Algorithm
- time : E*log(E) + E*log(E)
    - first E*log(E)
        - The maximum size of the priority queue can be E. (the number of edges)
        - Inside the loop, there is a pop operation that will take logE time.
    - second E*log(E)
       - For every neigh node, we need to traverse all its adjacent nodes where the number of nodes can be at most E
       - If we find any node unvisited, we will perform a push operation and for that, we need a logE

- space : O(E) + O(V) . . .  where E = no. of edges and V = no. of vertices. 
   - O(E) : occurs due to the size of the priority queue.
   - O(V) : due to the visited array
 
#### Krushkal's Algorithm
- time : E*logE
  - because we are sorting all the edges.
- space : V
  - since we are using DSU
