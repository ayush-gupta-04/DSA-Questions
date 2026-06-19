// OBV : 
// - a node which is a part of a cycle can never be a safe node.
// - means, we have to list all the nodes which are not the part of any cycle.
// - Topo Sort also does the same work, it doesn't let cyclic nodes add to the list.

// brute-force : 
// - we can check if a node is safe or not.
// - from every node we can traverse and check if we encounter that same node twice or not.
// - if encountered the same node .. not safe node.
// - if not then a safe node.

// Optimal : 
// - rather than going from every node to the terminal node.
// - we can perform a topo sort from terminal nodes, it will automatically add non-cyclic nodes.
// - we have to reverse the edges first.


class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //we are just reversing the edges and applying the topo sort.
        int V = graph.length;
        
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> Q = new LinkedList<>();
        int[] indegree = new int[V];
        List<Integer> safe = new ArrayList<>();

        for(int i = 0; i < V ; i++){
            adj.add(new ArrayList<>());
        }

        // reverse the edges.
        for(int i = 0 ; i < V ; i++){
            for(int node : graph[i]){
                adj.get(node).add(i);
                indegree[i]++;
            }
        }
        

        // add nodes having indegree 0
        for(int i = 0 ; i < V ; i++){
            if(indegree[i] == 0){
                Q.add(i);
            }
        }


        // perform khan's algo
        while(!Q.isEmpty()){
            int node = Q.poll();
            safe.add(node);
            for(int neigh : adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    Q.offer(neigh);
                }
            }
        }

        Collections.sort(safe);
        return safe;
    }
}
