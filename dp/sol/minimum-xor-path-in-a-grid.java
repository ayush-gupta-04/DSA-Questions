// TC -> N*M*1023
// SC -> N*M*1023
// We can't use the general grid DP.
// Why ??
// -> It is possible that taking a min val would become very large by xoring with another.
// -> It is also possible that taking a max val would becomde very small by xoring with another.
// -> XOR is not MONOTONIC.

// Solution :
// We need to know all possible values of xor at a cell.
// dp[i][j][x] -> At i,j ... is it possible to have a xor of x from 0,0 to i,j.
// DP transition.
// -> If we are at i,j .... 
// -> if dp[i-1][j][x]  then dp[i][j][x ^ grid[i][j]] = true.
// -> if dp[i][j-1][x]  then dp[i][j][x ^ grid[i][j]] = true.

// in the last iterate from 0 -> 1023 for the last cell.
// return the first possible value.

class Solution {
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] dp = new boolean[n][m][1024];

        dp[0][0][grid[0][0]] = true;

        for(int i = 0 ;i < n; i++){
            for(int j = 0 ; j < m ; j++){
                for(int x = 0 ; x <= 1023 ; x++){

                    if(i-1 >= 0 && dp[i-1][j][x]){
                        dp[i][j][x ^ grid[i][j]] = true;
                    }

                    if(j-1 >= 0 && dp[i][j-1][x]){
                        dp[i][j][x ^ grid[i][j]] = true;
                    }

                }
            }
        }

        for(int x = 0 ; x < 1024 ; x++){
            if(dp[n-1][m-1][x]){
                return x;
            }
        }

        return 0;
    }
}
