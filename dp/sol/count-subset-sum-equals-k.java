// ---------------- recur -----------------

class Solution {
    int fun(int i , int k , int[] nums){
        if(i == 0){
           if(k == 0){
               if(nums[0] == 0) return 2;
               return 1;
           }else{
               if(k == nums[0]) return 1;
               return 0;
           }
       }


       int np = fun(i - 1,k , nums);
       int p = 0;
       if(k >= nums[i]){
           p = fun(i - 1 , k - nums[i],nums);
       }
       return p + np;
    }
    public int perfectSum(int[] nums, int target) {
        // code here
        return fun(nums.length - 1, target , nums);
    }
}



// ------------------ memo -----------------------

import java.util.Arrays;

class Solution {
    int fun(int i, int k, int[] nums, int[][] dp) {
        // Base Case
        if (i == 0) {
            if (k == 0) {
                if (nums[0] == 0) return 2; // Both {} and {0} work
                return 1;                   // Only {} works
            } else {
                if (k == nums[0]) return 1;
                return 0;
            }
        }

        // Check if already computed
        if (dp[i][k] != -1) return dp[i][k];

        // Main logic
        int np = fun(i - 1, k, nums, dp);
        int p = 0;
        if (k >= nums[i]) {
            p = fun(i - 1, k - nums[i], nums, dp);
        }
        
        // Store the result in the table
        return dp[i][k] = p + np;
    }

    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return fun(n - 1, target, nums, dp);
    }
}



// ------------------ tab ----------------------

class Solution {
    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];

        // Initialize base cases for row 0 (i = 0)
        if (nums[0] == 0) {
            dp[0][0] = 2; // Both picking and skipping 0 yields a sum of 0
        } else {
            dp[0][0] = 1; // Only skipping yields a sum of 0
        }
        
        if (nums[0] != 0 && nums[0] <= target) {
            dp[0][nums[0]] = 1;
        }

        // Fill the rest of the grid bottom-up
        for (int i = 1; i < n; i++) {
            for (int k = 0; k <= target; k++) {
                int np = dp[i - 1][k];
                int p = 0;
                if (k >= nums[i]) {
                    p = dp[i - 1][k - nums[i]];
                }
                dp[i][k] = p + np;
            }
        }

        return dp[n - 1][target];
    }
}



// ---------------------------- sp opti ------------------


class Solution {
    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];

        // Initialize base case
        if (nums[0] == 0) {
            dp[0] = 2;
        } else {
            dp[0] = 1;
        }
        
        if (nums[0] != 0 && nums[0] <= target) {
            dp[nums[0]] = 1;
        }

        // Update the single array row by row
        for (int i = 1; i < n; i++) {
            // Loop backwards to ensure we use values from the previous state
            for (int k = target; k >= 0; k--) {
                int np = dp[k];
                int p = 0;
                if (k >= nums[i]) {
                    p = dp[k - nums[i]];
                }
                dp[k] = p + np;
            }
        }

        return dp[target];
    }
}





