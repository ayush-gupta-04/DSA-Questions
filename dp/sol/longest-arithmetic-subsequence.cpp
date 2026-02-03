// The Arithmetic Sussequence is determined by 2 things : 
// 1. The Common diff.
// 2. The start or the end.

// When we're at position i in the array, we want to know: what's the longest arithmetic subsequence that ends at nums[i]? But this question is incomplete - we also need to specify the common difference, because nums[i] could be the end of multiple arithmetic subsequences with different common differences.

// For each element nums[i], we consider pairing it with every previous element nums[k] to form a potential arithmetic sequence. The difference nums[i] - nums[k] becomes our common difference.

// The beautiful part is that if nums[k] was already part of an arithmetic subsequence with the same common difference, we can simply extend that subsequence by adding nums[i].


class Solution {
public:
    int longestArithSeqLength(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n , vector<int>(1001 , 1));
        int ans = 0;
        for(int i = 0;i < n; i++){
            for(int k = 0; k < i ; k++){
                int diff = nums[i] - nums[k] + 500;
                dp[i][diff] = max(dp[i][diff] , dp[k][diff] + 1);
                ans = max(ans , dp[i][diff]);
            }
        }
        return ans;
    }
};
