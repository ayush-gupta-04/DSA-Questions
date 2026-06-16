// time : N*N
// space : N

// a simple DP
class Solution {
    public int numTrees(int n) {
        if(n <= 2) return n;

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int node = 3; node <= n; node++){
            int cnt = 0;
            for(int p = 1; p <= node; p++){   // pivots in node
                int left = p-1;
                int right = node - p;
                cnt += dp[left]*dp[right];
            }
            dp[node] = cnt;
        }
        return dp[n];
    }
}
