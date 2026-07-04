// ----------------------- recur --------------------
// time : 2^(N + W)  In the worst-case scenario (like weight = 1), the recursion branches out up to W times for each item.
// space : W .. The maximum depth of the recursion stack occurs when reducing the capacity W by small weights.

class Solution {
    static int fun(int i , int w , int[] wt , int[] val){
       if(i == 0){
           if(w >= wt[i]) return val[i]*(w/wt[i]);
           return 0;
       }

       int npick = fun(i - 1, w , wt , val);
       int pick = 0;
       if(w >= wt[i]){
           pick = val[i] + fun(i , w - wt[i] , wt , val);
       }
       return Math.max(pick , npick);
   }

    public int knapSack(int val[], int wt[], int w) {
        // code here
        int n = val.length;
        return fun(n-1 , w,wt,val);
    }
}



// ------------------- memo ----------------------
// time : N x W
// space : W + ( N x W)

class Solution {
    static int fun(int i, int w, int[] wt, int[] val, int[][] dp) {
        if (i == 0) {
            if (w >= wt[i]) return val[i] * (w / wt[i]);
            return 0;
        }

        if (dp[i][w] != -1) return dp[i][w];

        int npick = fun(i - 1, w, wt, val, dp);
        int pick = 0;
        if (w >= wt[i]) {
            pick = val[i] + fun(i, w - wt[i], wt, val, dp);
        }
      
        return dp[i][w] = Math.max(pick, npick);
    }

    public int knapSack(int val[], int wt[], int w) {
        int n = val.length;
        int[][] dp = new int[n][w + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return fun(n - 1, w, wt, val, dp);
    }
}




// --------------------- tab ---------------------
// time :  N x W
// space :  N x W

class Solution {
    public int knapSack(int val[], int wt[], int w) {
        int n = val.length;
        int[][] dp = new int[n][w + 1];

        // Base case: Populate row 0 for item 0
        for (int cap = 0; cap <= w; cap++) {
            if (cap >= wt[0]) {
                dp[0][cap] = val[0] * (cap / wt[0]);
            }
        }

        // Build the table bottom-up
        for (int i = 1; i < n; i++) {
            for (int cap = 0; cap <= w; cap++) {
                int npick = dp[i - 1][cap];
                int pick = 0;
                if (cap >= wt[i]) {
                    pick = val[i] + dp[i][cap - wt[i]]; // Notice: staying on row 'i'
                }
                dp[i][cap] = Math.max(pick, npick);
            }
        }

        return dp[n - 1][w];
    }
}





// ------------------------ sp opti --------------
// time : N x W
// space : W

class Solution {
    public int knapSack(int val[], int wt[], int w) {
        int n = val.length;
        // A single 1D array to track capacities
        int[] dp = new int[w + 1];

        // Base case initialization for the 0th item
        for (int cap = 0; cap <= w; cap++) {
            if (cap >= wt[0]) {
                dp[cap] = val[0] * (cap / wt[0]);
            }
        }

        // Process the rest of the items moving forward
        for (int i = 1; i < n; i++) {
            for (int cap = 0; cap <= w; cap++) {
                int npick = dp[cap]; // Value from previous row
                int pick = 0;
                if (cap >= wt[i]) {
                    pick = val[i] + dp[cap - wt[i]]; // Newly updated value from current row
                }
                dp[cap] = Math.max(pick, npick);
            }
        }

        return dp[w];
    }
}
