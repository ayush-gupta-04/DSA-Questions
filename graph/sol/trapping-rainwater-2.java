// Approach - 1 : 
// -> we will call a BFS from a cell to check how much water it can hold.
// -> a very hectic approach.

// Approach - 2 :
// THOUGHT Process : 
// -> water will spill from the boundary for sure.
// -> The boundary cells will be our bottle-neck.
// -> The smallest cell in the boundary will be the out first bottle-neck.
// -> If i start from the smallest cell in the boundary , i am 100% sure about this : 
//    -> All the other boundaries are greater or equal to me.
//    -> I will be the only bottle-neck for a inner-cell.
//    -> If i just check how much water will be stored in a inner cell if i become a wall for it , 
//    ->    then i am 100% sure that no other walls can affect water stored on that inner wall.
//    ->    because i will be the most important bottle-neck for that inner cell.
// -> I will have all the boundary cells in a PQ (min-heap)
// -> If outer-wall is a higher-wall for an inner cell , then water will be stored on that inner-cell AND 
//       height of the inner-cell will become equals to the outer-wall ... and this wall will propagate.
// -> If curr-wall > inner-wall : 
// ->    -water stored on inner wall will be curr-wall - inner-wall.
// ->    -vis inner-wall.
// ->    -add inner-wall in PQ with height as curr-wall (because inner-wall will become equal as outer-wall).
// -> If curr-wall <= inner-wall : 
// ->    -water will not be stored on the inner-wall.
// ->    -vis inner-wall (because no other wall can make this inner-wall to store water on top of it .. because curr-wall will not let it happen .. it is the bottle neck.)
// ->    -add inner-wall in PQ with height as inner-wall (because it will become new boundary for further inner walls).

class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};

    public int trapRainWater(int[][] H) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y) -> Integer.compare(x[0] , y[0]));
        int n = H.length;
        int m = H[0].length;
        boolean[][] vis = new boolean[n][m];

        // filling PQ with the boundary cells;
        for(int i = 0;i < n; i++){
            for(int j = 0 ; j < m ;j++){
                if(i == 0 || i == n-1 || j == 0 || j == m-1){
                    pq.offer(new int[]{H[i][j] , i , j});
                    vis[i][j] = true;
                }
            }
        }
        int water = 0;
    
        while(!pq.isEmpty()){
            int[] d = pq.poll();
            int wall = d[0];
            int r = d[1];
            int c = d[2];

            for(int i = 0 ;i < 4 ; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(valid(nr,nc,n,m) && !vis[nr][nc]){
                    vis[nr][nc] = true;
                    if(H[nr][nc] < wall){
                        water += wall - H[nr][nc];
                        pq.offer(new int[]{wall , nr, nc});
                    }else{
                        pq.offer(new int[]{H[nr][nc], nr,nc});
                    }
                }
            }
        }

        return water;
    }
    boolean valid(int r, int c, int n , int m){
        return (r >= 0 && r < n  && c >= 0 && c < m);
    }
}
