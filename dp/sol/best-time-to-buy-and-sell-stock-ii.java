// ------------------ recur -----------------
// time : 2^N
// space : N

class Solution {
    int fun(int i,int turn, int[] nums){
        if(i == nums.length) return 0;

        if(turn == 0){
            int buy = -1*nums[i] + fun(i+1, 1, nums);
            int nBuy = fun(i+1, 0, nums);
            return Math.max(buy, nBuy);
        }

        int sell = nums[i] + fun(i+1, 0, nums);
        int nSell = fun(i+1, 1, nums);

        return Math.max(sell , nSell);
    }
    public int maxProfit(int[] prices) {
        return fun(0, 0, prices);
    }
}



// ---------------------- memo ------------------------------
// time : N x 2
// space : (N x 2) + (N recur st)


import java.util.Arrays;

class Solution {
    int fun(int i, int turn, int[] nums, int[][] dp){
        if(i == nums.length) return 0;
        
        // If we already calculated this state, just return it!
        if(dp[i][turn] != -1) return dp[i][turn];

        if(turn == 0){ // Buy turn
            int buy = -1 * nums[i] + fun(i + 1, 1, nums, dp);
            int nBuy = fun(i + 1, 0, nums, dp);
            return dp[i][turn] = Math.max(buy, nBuy);
        }

        // Sell turn
        int sell = nums[i] + fun(i + 1, 0, nums, dp);
        int nSell = fun(i + 1, 1, nums, dp);

        return dp[i][turn] = Math.max(sell, nSell);
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp array stores: [day][turn] -> turn can only be 0 (Buy) or 1 (Sell)
        int[][] dp = new int[n][2];
        for(int[] row : dp) Arrays.fill(row, -1);
        
        return fun(0, 0, prices, dp);
    }
}


// -------------------- tab -------------------
// time : N x 2
// space : N x 2


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // Extra row for the base case (day == n)
        int[][] dp = new int[n + 1][2]; 

        // Base case: on day 'n', profit is 0 (already initialized to 0)
        dp[n][0] = 0;
        dp[n][1] = 0;

        // Loop backwards from the last day to day 0
        for (int i = n - 1; i >= 0; i--) {
            for (int turn = 0; turn <= 1; turn++) {
                if (turn == 0) { // Buy turn
                    int buy = -prices[i] + dp[i + 1][1];
                    int nBuy = dp[i + 1][0];
                    dp[i][turn] = Math.max(buy, nBuy);
                } else { // Sell turn
                    int sell = prices[i] + dp[i + 1][0];
                    int nSell = dp[i + 1][1];
                    dp[i][turn] = Math.max(sell, nSell);
                }
            }
        }
        // The answer will be at the start: day 0, ready to buy
        return dp[0][0];
    }
}



// ---------------------- sp opti ---------------
// time : N
// space : 1


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int nextBuy = 0;
        int nextSell = 0;

        // Loop backwards from the last day to day 0
        for (int i = n - 1; i >= 0; i--) {
            int currBuy = 0;
            int currSell = 0;
            for (int turn = 0; turn <= 1; turn++) {
                if (turn == 0) { // Buy turn
                    int b = -prices[i] + nextSell;
                    int nb = nextBuy;
                    currBuy = Math.max(b, nb);
                } else { // Sell turn
                    int s = prices[i] + nextBuy;
                    int ns = nextSell;
                    currSell = Math.max(s, ns);
                }
            }
            nextBuy = currBuy;
            nextSell = currSell;
        }
        return nextBuy;
    }
}




