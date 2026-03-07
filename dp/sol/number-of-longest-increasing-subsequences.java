// ------------------------------------ BOTTOM-UP DP ------------------------------------------------------
// TC -> O(N^2)
// SC -> O(N)
// Normally, we solve the Number of LIS using a $O(N^2)$ DP approach. 
// We maintain two arrays for an input array nums:
// dp[i]: The length of the LIS ending at index i.
// count[i]: The number of LIS ending at index i.
// The Logic:
// For every element nums[i], we look back at all previous elements nums[j] (where j < i).
// If nums[j] < nums[i] (meaning we can extend the subsequence):
// 1. If dp[j] + 1 > dp[i]: We found a strictly longer subsequence.
//    We update dp[i] = dp[j] + 1 and carry over the count: count[i] = count[j].
// 2. If dp[j] + 1 == dp[i]: We found another way to make the same longest subsequence. 
//    We add the counts together: count[i] = count[i] + count[j].
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
