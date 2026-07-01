// --------------- recursion ------------------
// time : 2^(n + m)
// space : n + m

class Solution {
    public int fun(int i, int j){
        if(i == 0 && j == 0) return 1;
        int up = (i-1 >= 0 ? fun(i-1 , j) : 0);
        int left = (j-1 >= 0 ? fun(i , j-1) : 0);
        return up + left;
    }
    public int uniquePaths(int n, int m) {
        return fun(n-1, m-1);
    }
}



// -------------- memo ------------------
// time : n*m
// space : n*m + n+m
// - n + m : recur call
// - n * m : dp[][]

class Solution {
    public int fun(int i, int j, int[][] dp) {
        if (i == 0 && j == 0) return 1;

        if (dp[i][j] != -1) return dp[i][j];
        
        int up = (i - 1 >= 0 ? fun(i - 1, j, dp) : 0);
        int left = (j - 1 >= 0 ? fun(i, j - 1, dp) : 0);
        
        return dp[i][j] = up + left;
    }

    public int uniquePaths(int n, int m) {
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // Initialize table with -1
        }
        return fun(n - 1, m - 1, dp);
    }
}




// ------------------------ tab -----------------------
// time : n x m
// space : n x m


class Solution {
    public int uniquePaths(int n, int m) {
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {   // base case.
                    dp[i][j] = 1;
                } else {
                    int up = (i - 1 >= 0) ? dp[i - 1][j] : 0;
                    int left = (j - 1 >= 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }
        
        return dp[n - 1][m - 1];
    }
}





// --------------------- space opti -----------------------
// time : n x m
// space : m

class Solution {
    public int uniquePaths(int n, int m) {
        // We only need an array representing the previous row 📏
        int[] prev = new int[m];
        
        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = 1;
                } else {
                    int up = prev[j]; // Value from the row above
                    int left = (j - 1 >= 0) ? curr[j - 1] : 0; // Value from the left 
                    curr[j] = up + left;
                }
            }
            prev = curr; // Move down to the next row 
        }
        
        return prev[m - 1];
    }
}
