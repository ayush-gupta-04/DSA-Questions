// -------------- recur -------------
// time :
// space : 

// fun(i,amt) -> how many minimum coins should i take from (0 -> i) to form amt.

class Solution {
    int INF = 1_000_000_000;
    int fun(int i , int amt , int[] nums){
        if(amt == 0) return 0;
        if(i == 0){
            if(amt % nums[0] == 0) return amt / nums[0];
            else return INF;
        }

        int nt = fun(i - 1 , amt , nums);
        int t = INF;
        if(amt >= nums[i]){
            t = 1 + fun(i , amt - nums[i],nums);  // take it and stay here.
        }
        return Math.min(t , nt);
    }
    public int coinChange(int[] nums, int amt) {
        int n = nums.length;
        int ans = fun(n-1 , amt , nums);
        return ans == INF ? -1 : ans;
    }
}




// --------------------------- memo ------------------------

import java.util.Arrays;

class Solution {
    int INF = 1_000_000_000;
    
    int fun(int i, int amt, int[] nums, int[][] dp) {
        if (amt == 0) return 0;
        if (i == 0) {
            if (amt % nums[0] == 0) return amt / nums[0];
            return INF;
        }

        // 1. Check if already calculated
        if (dp[i][amt] != -1) return dp[i][amt];

        int nt = fun(i - 1, amt, nums, dp);
        int t = INF;
        if (amt >= nums[i]) {
            t = 1 + fun(i, amt - nums[i], nums, dp);  // take it and stay here.
        }
        
        // 2. Store and return the result
        return dp[i][amt] = Math.min(t, nt);
    }
    
    public int coinChange(int[] nums, int amt) {
        int n = nums.length;
        int[][] dp = new int[n][amt + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        int ans = fun(n - 1, amt, nums, dp);
        return ans == INF ? -1 : ans;
    }
}



// -------------------------- tab -----------------------

class Solution {
    int INF = 1_000_000_000;
    
    public int coinChange(int[] nums, int amt) {
        int n = nums.length;
        int[][] dp = new int[n][amt + 1];
        
        // Base Case 1: If target amount is 0, 0 coins are needed
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        
        // Base Case 2: For the first coin (index 0), check divisibility
        for (int a = 1; a <= amt; a++) {
            if (a % nums[0] == 0) dp[0][a] = a / nums[0];
            else dp[0][a] = INF;
        }
        
        // Fill the rest of the table bottom-up
        for (int i = 1; i < n; i++) {
            for (int a = 1; a <= amt; a++) {
                int nt = dp[i - 1][a];
                int t = INF;
                if (a >= nums[i]) {
                    t = 1 + dp[i][a - nums[i]]; // Notice: staying on row 'i'
                }
                dp[i][a] = Math.min(t, nt);
            }
        }
        
        int ans = dp[n - 1][amt];
        return ans == INF ? -1 : ans;
    }
}



// --------------------------- space opti ---------------------

class Solution {
    int INF = 1_000_000_000;
    
    public int coinChange(int[] nums, int amt) {
        int n = nums.length;
        // We only need a single 1D array!
        int[] prev = new int[amt + 1];
        
        // Base Case initialization for index 0
        prev[0] = 0;
        for (int a = 1; a <= amt; a++) {
            if (a % nums[0] == 0) prev[a] = a / nums[0];
            else prev[a] = INF;
        }
        
        // Update the row dynamically from left to right
        for (int i = 1; i < n; i++) {
            for (int a = 1; a <= amt; a++) {
                int nt = prev[a]; // Value from the previous iteration
                int t = INF;
                if (a >= nums[i]) {
                    t = 1 + prev[a - nums[i]]; // Newly updated value in the same row
                }
                prev[a] = Math.min(t, nt);
            }
        }
        
        int ans = prev[amt];
        return ans == INF ? -1 : ans;
    }
}
