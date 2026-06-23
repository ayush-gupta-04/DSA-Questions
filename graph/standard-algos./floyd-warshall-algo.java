// time : V³
// space : V²

// used to find the Shortest Path from Every Node to Every Node.
// mutisource shortest path.
// can also detect -ve cycle.

import java.util.*;

public class Run {
   static void floyd(List<List<int[]>> adj, int V) {
        int INF = 1_000_000_000;
        //just making the adj matrix.
        int[][] mat = new int[V][V];
        for(int i = 0 ; i < V ; i++){
            for(int j = 0 ; j < V; j++){
                if(i == j){
                    mat[i][j] = 0;
                }else{
                    mat[i][j] = INF;
                }
            }
        }
        for(int i = 0; i < V ; i++){
            for(int[] a : adj.get(i)){
                int u = i;
                int v = a[0];
                int wt = a[1];
                mat[u][v] = wt;
            }
        }


        //main algo.
        for(int via = 0 ; via < V ; via ++){
            for(int i = 0; i < V; i++){
                for(int j = 0 ; j < V ; j++){
                    if(mat[i][via] == INF || mat[via][j] == INF){
                        continue;
                    }
                    mat[i][j] = Math.min(mat[i][j] , mat[i][via] + mat[via][j]);
                }
            }
        }



        // detecting a -ve cycle.
        for(int i = 0; i < V; i++){
            if(mat[i][i] < 0){
                System.out.println("-ve cycle detected");
            }
        }
   }

   public static void main(String[] args) {
      floyd(Arrays.asList(
              Arrays.asList(new int[]{1,5}),
              Arrays.asList(new int[]{2,-2},new int[]{5,-3}),
              Arrays.asList(new int[]{4,3}),
              Arrays.asList(new int[]{2,6}, new int[]{4,-2}),
              Arrays.asList(),
              Arrays.asList(new int[]{3,1})
      ),6);
   }
}

