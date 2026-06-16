// time : 4*N*N
// space : N*N

class Solution {
    boolean valid(int r , int c , int n , int m){
        return (r >= 0 && r < n && c >= 0  && c < m);
    }
    public int orangesRotting(int[][] grid) {
        int[] dr = {0,0,-1,1};
        int[] dc = {1,-1,0,0};
        int n = grid.length;
        int m = grid[0].length;
        int total = 0;   // total oranges (fresh + rotten)
        int rotten = 0;  // total how many oranges become rotten.

        Deque<int[]> Q = new ArrayDeque<>();
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m ; j++){

                if(grid[i][j] != 0) total++;

                if(grid[i][j] == 2){
                    Q.offerLast(new int[]{i,j});
                }
            }
        }

        int days = 0;

        while(!Q.isEmpty()){
            int size = Q.size();

            for(int i = 0 ; i < size ; i++){
                int[] pair = Q.poll();
                int r = pair[0];
                int c = pair[1];
                rotten++;

                //if neigh are 1 just rot them and add them to the Q.
                for(int k = 0; k < 4 ; k++){
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if(valid(nr,nc,n,m) && grid[nr][nc] == 1){
                        grid[nr][nc] = 2;
                        Q.offer(new int[]{nr,nc});
                    }
                }
            }

            // Only increase time if we still have new oranges to process
            if(!Q.isEmpty()) days++;
        }

        // If all oranges are rotted, return days; otherwise return -1
        return total == rotten ? days : -1;
    }
}
