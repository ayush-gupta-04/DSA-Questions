// ------------------- recur --------------------
// time : 9^N
// space : N

class Solution {
    boolean valid(int c1, int c2, int m){
        return (c1 >= 0 && c2 >= 0 && c1 < m && c2 < m);
    }
    int fun(int r , int c1, int c2, int[][] grid){
        if(r == grid.length-1){
            return ((c1==c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
        }

        int max = 0;
        for(int d1 = -1; d1 <= 1; d1++){
            for(int d2 = -1 ; d2 <= 1 ; d2++){
                int nc1 = c1 + d1;
                int nc2 = c2 + d2;
                if(!valid(nc1,nc2,grid[0].length)) continue;
                max = Math.max(max , fun(r+1 , nc1, nc2 , grid));
            }
        }
        return max + ((c1==c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
    }
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return fun(0,0,m-1,grid);
    }
}




// -------------------- memo --------------------
// time : N x M x M
// space : N + (N x M x M)




import java.util.*;

class Solution {
    boolean valid(int c1, int c2, int m){
        return (c1 >= 0 && c2 >= 0 && c1 < m && c2 < m);
    }
    
    int fun(int r , int c1, int c2, int[][] grid, int[][][] dp){
        if(r == grid.length-1){
            return ((c1==c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
        }

        // If already computed, return it
        if(dp[r][c1][c2] != -1) return dp[r][c1][c2];

        int max = 0;
        for(int d1 = -1; d1 <= 1; d1++){
            for(int d2 = -1 ; d2 <= 1 ; d2++){
                int nc1 = c1 + d1;
                int nc2 = c2 + d2;
                if(!valid(nc1, nc2, grid[0].length)) continue;
                max = Math.max(max , fun(r+1 , nc1, nc2 , grid, dp));
            }
        }
        
        return dp[r][c1][c2] = max + ((c1==c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
    }
    
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][m];
        
        for(int[][] slice : dp) {
            for(int[] row : slice) {
                Arrays.fill(row, -1);
            }
        }
        return fun(0, 0, m-1, grid, dp);
    }
}





// ------------------------- tab ---------------------------

// time : N x M x M
// space :  N x M x M




class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][m];

        // 1. Base Case: Fill the last row
        for (int c1 = 0; c1 < m; c1++) {
            for (int c2 = 0; c2 < m; c2++) {
                if (c1 == c2) dp[n - 1][c1][c2] = grid[n - 1][c1];
                else dp[n - 1][c1][c2] = grid[n - 1][c1] + grid[n - 1][c2];
            }
        }

        // 2. Outer loop moving bottom-up from row n-2 down to 0
        for (int r = n - 2; r >= 0; r--) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int c2 = 0; c2 < m; c2++) {
                    
                    int max = 0;
                    // Explore all 9 next step combinations
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int nc1 = c1 + d1;
                            int nc2 = c2 + d2;
                            
                            if (nc1 >= 0 && nc1 < m && nc2 >= 0 && nc2 < m) {
                                max = Math.max(max, dp[r + 1][nc1][nc2]);
                            }
                        }
                    }
                    
                    int cherries = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
                    dp[r][c1][c2] = cherries + max;
                }
            }
        }

        return dp[0][0][m - 1];
    }
}



// -------------------------- space opti ---------------------
// time : N x M x M
// space : M x M



class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        // 2D grid representing the row right beneath the current one
        int[][] next = new int[m][m];

        // Initialize with the last row
        for (int c1 = 0; c1 < m; c1++) {
            for (int c2 = 0; c2 < m; c2++) {
                if (c1 == c2) next[c1][c2] = grid[n - 1][c1];
                else next[c1][c2] = grid[n - 1][c1] + grid[n - 1][c2];
            }
        }

        // Loop upwards
        for (int r = n - 2; r >= 0; r--) {
            int[][] curr = new int[m][m];
            for (int c1 = 0; c1 < m; c1++) {
                for (int c2 = 0; c2 < m; c2++) {
                    
                    int max = 0;
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int nc1 = c1 + d1;
                            int nc2 = c2 + d2;
                            if (nc1 >= 0 && nc1 < m && nc2 >= 0 && nc2 < m) {
                                max = Math.max(max, next[nc1][nc2]);
                            }
                        }
                    }
                    
                    int cherries = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
                    curr[c1][c2] = cherries + max;
                }
            }
            next = curr; // Slide the row window upwards
        }

        return next[0][m - 1];
    }
}
