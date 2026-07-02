// ------------------------ recur ------------------------
// time : M * (3^N) ... n = rows, m = cols
// - for M bottom rows we are calling the recursion.

// space : N

class Solution {
    int MIN = 1_000_000_000;
    int fun(int i , int j, int[][] mat){
        if(i == 0) return mat[i][j];

        int u = fun(i - 1 , j ,mat);
        int ld = (j-1 >= 0) ? fun(i - 1 , j - 1 , mat) : MIN;
        int rd = (j + 1 < mat[0].length) ? fun(i - 1, j + 1 , mat) : MIN;

        return mat[i][j] + Math.min(u , Math.min(ld , rd));
    }
    public int minFallingPathSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int minAns = MIN;
        for(int j = 0 ; j < m ; j++){
            minAns = Math.min(minAns,fun(n-1,j,mat));
        }
        return minAns;
    }
}



// --------------------- memo ------------------
// time : N*M
// space : N + N*M
// - N : recur st.
// - N*M : dp[][]


import java.util.*;

class Solution {
    int MIN = 1_000_000_000;

    int fun(int i, int j, int[][] mat, int[][] dp) {
        // Base case: reached the top row
        if (i == 0) return mat[i][j];

        // Return if already computed
        if (dp[i][j] != -1) return dp[i][j];

        int u = fun(i - 1 , j ,mat, dp);
        int ld = (j-1 >= 0) ? fun(i - 1 , j - 1 , mat, dp) : MIN;
        int rd = (j + 1 < mat[0].length) ? fun(i - 1, j + 1 , mat, dp) : MIN;

        return dp[i][j] = mat[i][j] + Math.min(u, Math.min(ld, rd));
    }

    public int minFallingPathSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);

        int minAns = MIN;
        for (int j = 0; j < m; j++) {
            minAns = Math.min(minAns, fun(n - 1, j, mat, dp));
        }
        return minAns;
    }
}




// -------------------- tab -----------------------
// time : N*M
// space : N*M


// main loop : 
// i : n-1 -> 0   ... -> ... 0 -> n-1
// j : m-1 -> 0   ... -> ... 0 -> m-1

// base case :
// j = 0 -> m-1
//    i = 0;

// inner for loop : 
// i = 1 -> n-1
//   j = 0 -> m


class Solution {
    int MIN = 1_000_000_000;

    public int minFallingPathSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];

        // Base case: Copy the first row as it is
        for (int j = 0; j < m; j++) {
            dp[0][j] = mat[0][j];
        }

        // Fill the DP table row by row
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int u = dp[i - 1][j];
                int ld = (j - 1 >= 0) ? dp[i - 1][j - 1] : MIN;
                int rd = (j + 1 < m) ? dp[i - 1][j + 1] : MIN;

                dp[i][j] = mat[i][j] + Math.min(u, Math.min(ld, rd));
            }
        }

        // The answer will be the minimum value in the last row
        int minAns = MIN;
        for (int j = 0; j < m; j++) {
            minAns = Math.min(minAns, dp[n - 1][j]);
        }
        return minAns;
    }
}



// -------------------- space opti ------------------
// time : N*M
// space : M



import java.util.*;

class Solution {
    int MIN = 1_000_000_000;

    public int minFallingPathSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        // Keeps track of the values from the previous row
        int[] prev = new int[m];

        // Base Case: Initialize prev with the first row of the matrix
        for (int j = 0; j < m; j++) {
            prev[j] = mat[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                int u = prev[j];
                int ld = (j - 1 >= 0) ? prev[j - 1] : MIN;
                int rd = (j + 1 < m) ? prev[j + 1] : MIN;

                curr[j] = mat[i][j] + Math.min(u, Math.min(ld, rd));
            }
            prev = curr; // Move down: current row becomes the previous row for next iteration
        }

        // Find the minimum value in the last calculated row
        int minAns = MIN;
        for (int j = 0; j < m; j++) {
            minAns = Math.min(minAns, prev[j]);
        }
        return minAns;
    }
}






