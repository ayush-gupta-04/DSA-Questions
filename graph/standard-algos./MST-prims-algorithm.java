// time : E*log(E) + E*log(E)
// - first E*log(E)
//    - The maximum size of the priority queue can be E.
//    - Inside the loop, there is a pop operation that will take logE time.
// - second E*log(E)
//    - For every neigh node, we need to traverse all its adjacent nodes where the number of nodes can be at most E
//    - If we find any node unvisited, we will perform a push operation and for that, we need a logE

// space : O(E) + O(V), where E = no. of edges and V = no. of vertices. 
// - O(E) : occurs due to the size of the priority queue.
// - O(V) : due to the visited array


import java.util.*;

public class Prims {
   static void floyd(List<List<int[]>> adj, int V) {
        int MSTWeight = 0;
        boolean[] inMST = new boolean[V];
        List<int[]> MSTList = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> Integer.compare(x[2], y[2])); // accoring to the weight.

        pq.offer(new int[]{0,-1,0});  // {node, parent, weight}
        // do not visit the src node now.

        while(!pq.isEmpty()){
            int[] currEdge = pq.poll();
            int node = currEdge[0];
            int parent = currEdge[1];
            int wt = currEdge[2];

            if(inMST[node]) continue;   // some other smaller edge containing this node has already been added to MST.
            inMST[node] = true;

            if(parent != -1){
                // add this edge to the MSTList;
                MSTList.add(new int[]{parent , node});
                MSTWeight += wt;
            }

            for(int[] neigh : adj.get(node)){
                int neighNode = neigh[0];
                int neighWt = neigh[1];
                if(inMST[neighNode]) continue;
                
                pq.offer(new int[]{neighNode , node, neighWt});
            }
        }
        for(int[] a : MSTList) System.out.println(Arrays.toString(a));
        System.out.println("total MST weight : " + MSTWeight);
   }

   public static void main(String[] args) {
      floyd(Arrays.asList(
              Arrays.asList(new int[]{1,4} , new int[]{2,1}),
              Arrays.asList(new int[]{0,4},new int[]{2,3}),
              Arrays.asList(new int[]{0,1}, new int[]{1,3})
      ),3);
   }
}

