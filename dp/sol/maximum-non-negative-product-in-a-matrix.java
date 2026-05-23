// Intuition : 
// This problem is like Minimum XOR in grid.
// we can't carry the maximum at a point .. because it is not monotonic.
// we have to have all the numbers that are possible at a point.

// Approach : 1
// Have all the numbers that are possible at a point.
// See what all numbers were possible in the up cell and left cell..and multiply with this cell value and store./
// it will give MLE ... since we are storing all the possible values.

// Approach : 2
// Do we need all the values at a point ??
// No ... we just need the minimum and maximum at a point.
// so we make 2 dp .. a maxDp and a minDp.

// Pitfall : 
// - max at a cell = max(up max * val , left max * val , up min * val , left min * val);
// - same for min at a cell.

class Solution {
    public int maxProductPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][] maxDp = new long[n][m];
        long[][] minDp = new long[n][m];

        maxDp[0][0] = (long)grid[0][0];
        minDp[0][0] = (long)grid[0][0];

        int MOD = 1_000_000_007;

        for(int i = 0;i < n;i++){
            for(int j = 0; j< m;j++){
                if(i==0 && j==0) continue;
                
                long val = (long)grid[i][j];
                long max = Long.MIN_VALUE;
                long min = Long.MAX_VALUE;
                
                if (i - 1 >= 0) {
                    long p1 = maxDp[i-1][j] * val;
                    long p2 = minDp[i-1][j] * val;
                    
                    max = Math.max(max, Math.max(p1, p2));
                    min = Math.min(min, Math.min(p1, p2));
                }

                if (j - 1 >= 0) {
                    long p1 = maxDp[i][j-1] * val;
                    long p2 = minDp[i][j-1] * val;
                    
                    max = Math.max(max, Math.max(p1, p2));
                    min = Math.min(min, Math.min(p1, p2));
                }
                maxDp[i][j] = max;
                minDp[i][j] = min;
            }
        }

        long max = maxDp[n-1][m-1];
        if(max >= 0) return (int)(max%MOD);
        return -1;
        
    }
}
