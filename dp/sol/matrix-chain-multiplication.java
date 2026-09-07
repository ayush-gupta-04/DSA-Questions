// ----------------------- Recur -----------------
// 10, 20, 30, 40, 50
//     i            j
//     k   ->   k         // movement of K.

// 10, 20, 30, 40, 50
//     i            j
//          k
// (10,20,30) X (30,40,50)
// (10x20, 20x30) X (30x40, 40x50)
// (10x30) X (30x50)

// A[i-1] x A[k] x A[j]

class Solution {
    static int solve(int i , int j, int[] nums){
        if(i == j) return 0;
        
        int min = 1_000_000_000;
        
        for(int k = i; k <= j-1 ; k++){
            int steps = nums[i-1]*nums[k]*nums[j] + solve(i,k,nums) + solve(k+1,j, nums);
            min = Math.min(min , steps);
        }
        return min;
    }
    static int matrixMultiplication(int nums[]) {
        // code here
        int n = nums.length;
        return solve(1,n-1, nums);
    }
}

// ----------------- tab ------------------

// i : 1 -> n-1 ... n
// j : n-1 -> i  ... n

// actual loop
// i : 1 -> n-1
// j : n-1 -> i


// base case : 
// for (i : 0 -> n-1)

// inner for loop : 
// i : n-1 -> 1
// j : i+1 -> n-1




class Solution {
    static int matrixMultiplication(int nums[]) {
        // code here
        int n = nums.length;
        
        int[][] dp = new int[n][n];

        // base case.
        for(int i = 0;i < n;i++){
            dp[i][i] = 0;
        }

      // inner for loop.
        for(int i = n-1; i >= 1; i--){
            for(int j = i+1; j <= n-1; j++){
                
                int min = 1_000_000_000;

                for(int k = i; k <= j-1 ; k++){
                    int steps = nums[i-1]*nums[k]*nums[j] + dp[i][k] + dp[k+1][j];
                    min = Math.min(min , steps);
                }
                dp[i][j] = min;
            }
        }
        
        return dp[1][n-1];
    }
}
