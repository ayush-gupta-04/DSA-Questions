class Solution {
    int topDown(int i, int j, List<Integer> cuts){
        if(i > j){
            return 0;
        }
        int min = 1_000_000_000; 
        for(int k = i; k <= j; k++){
            int cost = cuts.get(j+1) - cuts.get(i-1) + solve(i,k-1,cuts) + solve(k+1,j,cuts);
            min = Math.min(min , cost);
        }
        return min;
    }

    int bottomUp(List<Integer> cuts){
        int n = cuts.size();
        int[][] dp = new int[n][n];

        for(int i = n-2; i >= 1; i--){
            for(int j = i; j <= n-2; j++){
                int min = 1_000_000_000; 
                for(int k = i; k <= j; k++){
                    int cost = cuts.get(j+1) - cuts.get(i-1) + dp[i][k-1] + dp[k+1][j];
                    min = Math.min(min , cost);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n-2];
    }
    public int minCost(int n, int[] nums) {
        List<Integer> cuts = new ArrayList<>();
        cuts.add(0);
        for(int a : nums) cuts.add(a);
        cuts.add(n);

        Collections.sort(cuts);

        return bottomUp(cuts);
        // return topDown(0,cuts.size()-1, cuts);
    }
}
