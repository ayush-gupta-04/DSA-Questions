```
class Solution {
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if(n == 2) return 0;
        int[][] dist = new int[n][n];
        int maxDist = 0;

        //make a dist[][] to store the dist bet every pair of nodes.
        for(int i =0; i < n ; i++){
            for(int j = i + 1 ; j < n ; j++){
                int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                dist[i][j] = d;
                dist[j][i] = d;
                maxDist = Math.max(maxDist , d);
            }
        }

        int low = 0;
        int high = maxDist;

        while(low <= high){
            int mid = low + (high - low)/2;
            if(check(mid , dist , n)){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return high;
    }

    boolean check(int d , int[][] dist ,int n){
        //only create an egde bet any two nodes if dist < d.
        //and check if the graph made is bipartitite or not.

        List<List<Integer>> adj = new ArrayList<>();
        int[] color = new int[n];
        for(int i = 0 ; i < n ;i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = i + 1 ; j < n; j++){
                if(dist[i][j] < d){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }


        // since we don't know that is there only one component .. we must run this for every node.
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1 , adj , color)) return false;
            }
        }
        return true;
    }



    boolean dfs(int u, int c , List<List<Integer>> adj , int[] color) {
        color[u] = c;
        for (int v : adj.get(u)) {
            if (color[v] == 0) {
                if (!dfs(v, (c == 1) ? 2 : 1 , adj, color)) {
                    return false;
                }
            } else if (color[v] == c) {
                return false; 
            }
        }
        return true;
    }
}
```
