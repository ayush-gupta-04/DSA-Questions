 
// ---------------------- recursive solution --------------
class Solution {
    int solve(int i, int j, List<Integer> list){
        if(i > j){
            return 0;
        }
        int max = 0;
        for(int k = i; k <= j; k++){   // lets choose who was the remaining among [i->j]
            int coins = list.get(k)*list.get(i-1)*list.get(j+1) + solve(i,k-1,list) + solve(k+1,j,list);
            max = Math.max(max, coins);
        }
        return max;
    }
    public int maxCoins(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for(int a : nums) list.add(a);
        list.add(1);
        return solve(1, list.size()-2, list);
    }
}



// ----------------- Bottom Up DP ----------------

class Solution {
    public int maxCoins(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for(int a : nums) list.add(a);
        list.add(1);

        int n = list.size();
        int[][] dp = new int[n][n];

        for(int i = n-2; i >= 1; i--){
            for(int j = i ; j <= n-2; j++){
                int max = 0;
                for(int k = i; k <= j; k++){   // lets choose who was the remaining among [i->j]
                    int coins = list.get(k)*list.get(i-1)*list.get(j+1) + dp[i][k-1] + dp[k+1][j];
                    max = Math.max(max, coins);
                }
                dp[i][j] = max;
            }
        }
        return dp[1][n-2];
    }
}
