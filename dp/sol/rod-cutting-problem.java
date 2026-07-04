// ------------------- recur ----------------
// time : 2^n
// space : n

class Solution {
    int fun(int len , int[] price){
        if(len == 1) return price[len-1];
        
        int max = price[len-1];
        
        for(int i = 1 ; i < len ;i++){
            int left = fun(i,price);
            int right = fun(len - i , price);
            max = Math.max(max , left + right);
        }
        
        return max;
    }
    public int cutRod(int[] price) {
        // code here
        int n =price.length;
        return fun(n,price);
    }
}



// ------------------------------- memo -----------------------------------
// time : N x N
// space : N + (N x N)

import java.util.Arrays;

class Solution {
    int fun(int len, int[] price, int[] dp) {
        // Base case
        if (len == 1) return price[len - 1];
        
        // 1. Check if the result is already computed
        if (dp[len] != -1) return dp[len];
        
        int max = price[len - 1];
        
        for (int i = 1; i < len; i++) {
            int left = fun(i, price, dp);
            int right = fun(len - i, price, dp);
            max = Math.max(max, left + right);
        }
        
        // 2. Store the result in the dp array
        return dp[len] = max;
    }
    
    public int cutRod(int[] price) {
        int n = price.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1); // Initialize with -1
        
        return fun(n, price, dp);
    }
}


// ------------------------------- tab ---------------------------
// time : N x N
// space : N


class Solution {
    public int cutRod(int[] price) {
        int n = price.length;
        int[] dp = new int[n + 1];
        
        // Base Case: 
        dp[1] = price[0];
        
        // Build the solution for every length from 1 to n
        for (int len = 2; len <= n; len++) {
            int max = price[len - 1]; // Default: No cuts made
            
            // Look at all possible ways to split the current length
            for (int i = 1; i < len; i++) {
                max = Math.max(max, dp[i] + dp[len - i]);
            }
            dp[len] = max;
        }
        return dp[n];
    }
}
