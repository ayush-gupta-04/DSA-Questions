// --------------- recur -------------

class Solution {
    int fun(int i , int d , int[] A){
        if(d > 3){
            //do tnx ho chuke
            return 0;
        }

        if(i == A.length){
            return 0;
        }

        if(d % 2 == 0){
            //buy
            int buy = -A[i] + fun(i + 1 , d + 1 , A);
            int nbuy = fun(i + 1 , d , A);
            return Math.max(buy , nbuy);
        }else{
            int sell = A[i] + fun(i + 1 , d + 1 , A);
            int nsell = fun(i + 1 , d , A);
            return Math.max(sell , nsell);
        }
    }
    public int maxProfit(int[] A) {
        //even -> buy;
        //odd -> sell;
        return fun(0,0,A);
    }
}




// ------------------------- memo ----------------


import java.util.Arrays;

class Solution {
    int fun(int i, int d, int[] A, int[][] dp){
        if(d > 3) return 0; // 2 transactions completed
        if(i == A.length) return 0; // Out of days
        
        // If already calculated, return the cached result
        if(dp[i][d] != -1) return dp[i][d];

        if(d % 2 == 0){
            // Buy turn
            int buy = -A[i] + fun(i + 1, d + 1, A, dp);
            int nbuy = fun(i + 1, d, A, dp);
            return dp[i][d] = Math.max(buy, nbuy);
        } else {
            // Sell turn
            int sell = A[i] + fun(i + 1, d + 1, A, dp);
            int nsell = fun(i + 1, d, A, dp);
            return dp[i][d] = Math.max(sell, nsell);
        }
    }

    public int maxProfit(int[] A) {
        int n = A.length;
        // i ranges from 0 to n-1, d ranges from 0 to 3
        int[][] dp = new int[n][4];
        for(int[] row : dp) Arrays.fill(row, -1);
        
        return fun(0, 0, A, dp);
    }
}




// ----------------------------- tab -----------------------------------


class Solution {
    public int maxProfit(int[] A) {
        int n = A.length;
        // dp table of size (n+1) x 5 to securely handle base cases safely
        int[][] dp = new int[n + 1][5]; 

        // Base cases: day == n or d >= 4 are already initialized to 0 in Java

        // Loop backwards through days
        for (int i = n - 1; i >= 0; i--) {
            // Loop backwards through states
            for (int d = 3; d >= 0; d--) {
                if (d % 2 == 0) {
                    // Buy logic
                    int buy = -A[i] + dp[i + 1][d + 1];
                    int nbuy = dp[i + 1][d];
                    dp[i][d] = Math.max(buy, nbuy);
                } else {
                    // Sell logic
                    int sell = A[i] + dp[i + 1][d + 1];
                    int nsell = dp[i + 1][d];
                    dp[i][d] = Math.max(sell, nsell);
                }
            }
        }
        // Our final answer starts at day 0 with 0 transactions done
        return dp[0][0];
    }
}
