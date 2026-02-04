// Standard Pattern :
// If i have  (i -> ... <- j).
// To fill the dp table .. fill like this.
// -> fill all blocks of size 1 .. then 2 .. then 3.


// We have to ans that is there any way for alice to win or not.
// -> we can do a + for alice and a - for bob.
// state -> fun(i,j) : what is the maximum that alice can gain.

// -> If alice has choices .. she will try to maximize to win.
// -> If bob has choices .. If bob chooses anything then it will recude alice points
//    We will try to minimise the loss for alice so that she can win.

class Solution {
public:
    int fun(vector<int>& nums, int i , int j){
        int par = (nums.size() - (j - i + 1))%2;
        if(i == j){
            if(par == 1){
                return -nums[i];
            }
            return nums[i];
        }
        if(par == 1){
            return min(-nums[i] + fun(nums , i +1 , j) , -nums[j] + fun(nums,i,j-1));
        }
        return max(nums[i] + fun(nums , i +1 , j) , nums[j] + fun(nums,i,j-1));
    }
    bool stoneGame(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n,vector<int>(n,0));


        // base case;
        for(int i = 0;i < n; i++){
            int par = (n - 1)%2;
            if(par == 1){
                dp[i][i] = -nums[i];
            }else{
                dp[i][i] = nums[i];
            } 
        }

        for(int L = 2 ; L <= n ; L++){
            for(int i = 0,j = L-1 ; j < n ; i++ , j++){
                int par = (nums.size() - (j - i + 1))%2;
                if(par == 1){
                    dp[i][j] = min(-nums[i] + dp[i+1][j] , -nums[j] + dp[i][j-1]);
                }else{
                    dp[i][j] = max(nums[i] + dp[i+1][j] , nums[j] + dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1] > 0;
    }
};
