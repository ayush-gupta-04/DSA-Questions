// time : N
// space : 1


// since we have to buy once and sell once.
// for every day we could ask one question.
//   what was the minimum price to buy a stock in the past.
//   we will try to sell stock every day.

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;
        int buy = prices[0];

        for(int i = 1;i < n; i++){
            if(prices[i] > buy) maxProfit = Math.max(maxProfit , prices[i]-buy);
            buy = Math.min(buy , prices[i]);
        }

        return maxProfit;
    }
}
