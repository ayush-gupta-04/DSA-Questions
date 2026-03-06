// use the concept of .. number of ways to arrive at the destination.

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] ways = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(ways, 1);
        int max = 0;
        for(int i = 1 ; i < n ;i++){
            for(int j = 0 ; j < i ;j++){
                if(nums[i] > nums[j]){
                    if(dp[j] + 1 > dp[i]){
                        // a longer is found.
                        ways[i] = ways[j];
                        dp[i] = dp[j] + 1;
                    }else if(dp[j] + 1 == dp[i]){
                        ways[i] += ways[j];
                    }
                }
            }
            max = Math.max(max , dp[i]);
        }
        int cnt = 0;
        for(int i = 0 ;i < n; i++){
            if(dp[i] == max){
                cnt += ways[i];
            }
        }
        return cnt;
    }
}
