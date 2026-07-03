// Magic of dp[][] array.
// in order to find ans for (n-1 , target) , we generated a dp[][] which has the answer for any index and any target.

// in array we have S1 and S2
// range of S1 = 0, 1, 2, 3 ... totSum
// all values of S1 are not possible .. some are possible and we will find it from the dp[][] that we just got.
// diff = |S1 - S2|
//      = |S1 - (totSum - S1)|
//      = |2*S1 - totSum|

class Solution {

    // returns the dp[][].
    static boolean[][] fun(int arr[], int sum){
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
        
        return dp;
    }
    
    public int minDifference(int nums[]) {
        int n = nums.length;
        int sum = 0;
        for(int a : nums) sum += a;
        boolean[][] dp = fun(nums , sum);
        int minDiff = Integer.MAX_VALUE;
      
        for(int i = 0;i <= sum ; i++){
            if(dp[n-1][i]){
                int diff = Math.abs(2*i - sum);
                minDiff = Math.min(minDiff , diff);
            }
        }
        return minDiff; 
    }
}
