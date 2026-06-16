// ------------------- BFS ----------------------
// time : 


public class Main{
    // for dis-connected components of a graph.
    public boolean isCycle(List<List<Integer>> adj, int N, int src){
        boolean[] vis = new boolean[N];
        for(int i = 0;i < N;i++){
            if(!vis[i]){
                boolean doesCycleExist = checkCycle(adj, N, i, vis);
                if(doesCycleExist) return true;
            }
        }
        return false;
    }

    // main logic to check for cycle.
    public boolean checkCycle(List<List<Integer>> adj, int N, int src, boolean[] vis){

        Deque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{src , -1});
        vis[src] = true;

        while(!q.isEmpty()){
            int node = q.peekFirst()[0];
            int parent = q.peekFirst()[1];


            for(int neigh : adj.get(node)){
                //if a is not visited
                if(!vis[neigh]){
                    vis[neigh] = true;
                    q.offerLast(new int[]{neigh , node});
                }


                // if neigh is visited :
                //   Then it must be parent of node for no cycle.
                //   if not then it is a cycle.
                if(vis[neigh] && neigh != parent){
                    return true;
                }
            }
        }
        return false;
    }
}
