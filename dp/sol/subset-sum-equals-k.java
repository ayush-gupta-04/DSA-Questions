// ------------------ recur -------------------
// time : 2^N
// space : N

// we are doing early return here ... no problem.
class Solution {
    static boolean fun(int i , int k , int[] nums){
        // base case
        if(k == 0) return true;
        if(i == 0) return k == nums[i];
        
        // main logic
        boolean np = fun(i-1 , k , nums);
        if(np) return true;
        boolean p = false;
        if(k >= nums[i]){
            p = fun(i-1 , k - nums[i], nums);
        }
        
        return p;
    }
    static Boolean isSubsetSum(int arr[], int sum) {
        return fun(arr.length-1,sum,arr);
    }
}



// ---------------------- memo ------------------
// time : N x sum
// space :( N x sum ) + N (recur st)


import java.util.Arrays;

class Solution {
    static int fun(int i, int k, int[] nums, int[][] dp) {
        // Base cases
        if (k == 0) return 1; // 1 represents true
        if (i == 0) return (k == nums[i]) ? 1 : 0;
        
        // If already calculated, return the stored result
        if (dp[i][k] != -1) return dp[i][k];
        
        // Main logic
        int np = fun(i - 1, k, nums, dp);
        if (np == 1) return dp[i][k] = 1;
        
        int p = 0;
        if (k >= nums[i]) {
            p = fun(i - 1, k - nums[i], nums, dp);
        }
        
        // Store and return the result (1 for true, 0 for false)
        return dp[i][k] = (p == 1) ? 1 : 0;
    }

    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        // dp table initialized with -1 to represent uncalculated states
        int[][] dp = new int[n][sum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return fun(n - 1, sum, arr, dp) == 1;
    }
}




// ------------------ tab ---------------
// time : N x sum
// space : ( N x sum )

// main logic
// i : n-1 -> 0   ...  0 -> n-1
// k : sum -> 0   ...  0 -> sum

// base case : 
// k = 0
// i = 0 && k == nums[0]


// inner for loop :
// i : 1 -> n-1
//     k : 1 -> sum

class Solution {
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][sum + 1];
        
        // Base case 1: If target sum is 0, it's always true (empty subset)
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        
        // Base case 2: For the first element (index 0)
        if (arr[0] <= sum) {
            dp[0][arr[0]] = true;
        }
        
        // Fill the table iteratively
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= sum; k++) {
                boolean np = dp[i - 1][k]; // Not pick
                boolean p = false;         // Pick
                if (k >= arr[i]) {
                    p = dp[i - 1][k - arr[i]];
                }
                dp[i][k] = np || p;
            }
        }
        
        return dp[n - 1][sum];
    }
}



// -------------------- sp opti ----------------------
// time : N x sum
// space : ( sum )


class Solution {
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        // We only need two rows to store results
        boolean[] prev = new boolean[sum + 1];
        
        // Base case 1: If sum is 0, it's true
        prev[0] = true;
        
        // Base case 2: For the first element
        if (arr[0] <= sum) {
            prev[arr[0]] = true;
        }
        
        // Iteratively calculate the rest using only two rows
        for (int i = 1; i < n; i++) {
            boolean[] curr = new boolean[sum + 1];
            curr[0] = true; // Target sum 0 is always true
            
            for (int k = 1; k <= sum; k++) {
                boolean np = prev[k];
                boolean p = false;
                if (k >= arr[i]) {
                    p = prev[k - arr[i]];
                }
                curr[k] = np || p;
            }
            // Move current row to previous for the next iteration
            prev = curr;
        }
        
        return prev[sum];
    }
}

