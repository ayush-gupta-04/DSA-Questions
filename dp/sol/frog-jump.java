// ---------------------------- Recursion ---------------------
// time : 2^n
// space : n

class Solution {
    int fun(int i , int[] A){
        if(i==0) return 0;
        int a = Math.abs(A[i] - A[i-1]) + fun(i-1 , A);
        int b = Integer.MAX_VALUE;
        if(i >= 2){
            b = Math.abs(A[i] - A[i-2]) + fun(i-2 , A);
        }
        
        return Math.min(a,b);
    }
    int minCost(int[] height) {
        // code here
        int n = height.length;
        return fun(n-1,height);
    }
}




// ---------------------- memoisation -----------------------------
// time : N
// space : 2*N


class Solution {
    int fun(int i , int[] A,int[] dp){
        if(i==0) return 0;
        
        if(dp[i] != -1) return dp[i];
        
        int a = Math.abs(A[i] - A[i-1]) + fun(i-1 , A, dp);
        int b = Integer.MAX_VALUE;
        if(i >= 2){
            b = Math.abs(A[i] - A[i-2]) + fun(i-2 , A, dp);
        }
        
        return dp[i] = Math.min(a,b);
    }
    int minCost(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return fun(n-1,height,dp);
    }
}



// ------------------------- Tabulation -------------------------------

// main loop : 
// i : n-1 -> 0 ... recur
// i : 0 -> n-1 ... tab

// base case : 
// i = 0

// inner for loop : 
// i = 1 -> n-1

class Solution {
    int minCost(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
       
        dp[0] = 0;
        
        for(int i = 1 ; i < n ; i++){
            int a = Math.abs(A[i] - A[i-1]) + dp[i-1];
            int b = Integer.MAX_VALUE;
            if(i >= 2){
                b = Math.abs(A[i] - A[i-2]) + dp[i-2];
            }
            dp[i] = Math.min(a,b);
        }
        return dp[n-1];
    }
}
