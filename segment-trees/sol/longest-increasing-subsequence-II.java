// -------------------- METHOD-1 : DP------------------
// TC -> N^2.
// SC -> N.
// a big bottleneck


class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp , 1);
        for(int i = 0;i < n; i++){
            for(int j = 0 ;j < i;j++){
                if(nums[i] > nums[j] && (nums[i] - nums[j]) <= k){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
        }

        int max = 1;
        for(int i = 0;i < n; i++){
            max = Math.max(dp[i] ,max);
        }
        return max;
    }
}
