// ----------------- recur --------------------

class Solution {
    boolean fun(int i , int k , int[] nums){
        if(k == 0) return true;
        if(i == 0) return nums[0] == k;

        boolean nt = fun(i-1, k , nums);
        boolean t = false;
        if(nums[i] <= k){
            t = fun(i-1, k - nums[i] , nums);
        }

        return nt || t;
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int a : nums) sum += a;
        if(sum % 2 == 1) return false;
        return fun(nums.length -1 , sum/2 , nums);

    }
}


// ------------------ tab ---------------------

class Solution {
    boolean fun(int k , int[] nums){
        int n = nums.length;
        boolean[][] dp = new boolean[n][k + 1];

        for(int i = 0; i < n; i++) dp[i][0] = true;
        if(nums[0] <= k) dp[0][nums[0]] = true;

        for(int i = 1 ; i < n; i++){
            for(int target = 1 ; target <= k ; target++){
                boolean nt = dp[i-1][target];
                boolean t = false;
                if(nums[i] <= target){
                    t = dp[i-1][target - nums[i]];
                }
                dp[i][target] = nt || t;
            }
        }

        return dp[n-1][k];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int a : nums) sum += a;
        if(sum % 2 == 1) return false;
        return fun(sum/2 , nums);
    }
}



// -------------------- sp opti ------------------


class Solution {
    boolean fun(int k , int[] nums){
        int n = nums.length;
        boolean[] dp = new boolean[k + 1];

        for(int i = 0; i < n; i++) dp[0] = true;
        if(nums[0] <= k) dp[nums[0]] = true;

        for(int i = 1 ; i < n; i++){
            boolean[] temp = new boolean[k+1];
            for(int target = 1 ; target <= k ; target++){
                boolean nt = dp[target];
                boolean t = false;
                if(nums[i] <= target){
                    t = dp[target - nums[i]];
                }
                temp[target] = nt || t;
            }
            dp = temp;
        }

        return dp[k];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int a : nums) sum += a;
        if(sum % 2 == 1) return false;
        return fun(sum/2 , nums);
    }
}
