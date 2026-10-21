// ----------------- Memo -----------------

class Solution {
    int solve(int i , String s, boolean[][] check, int[] dp){
        if(i == s.length()) return 0;

        if(dp[i] != -1) return dp[i];

        int min = 1_00_000_000;
        for(int k = i; k < s.length(); k++){
            if(!check[i][k]) continue;
            min = Math.min(min, 1 + solve(k+1, s,check, dp));
        }

        return dp[i] = min;
    }
    public int minCut(String s) {
        int n = s.length();
        boolean[][] check = new boolean[n][n];

        for(int L = 1; L <= n ; L++){
            for(int i = 0; i + L - 1 < n; i++){
                int j = i + L -1;
                if(i==j){
                    check[i][j] = true;
                }else if(j == i + 1){
                    check[i][j] = (s.charAt(i)==s.charAt(j));
                }else{
                    check[i][j] = (s.charAt(i)==s.charAt(j) && check[i+1][j-1]);
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, s, check, dp) - 1;
    }
}





// ------------------------- Tab --------------

class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] check = new boolean[n][n];

        for(int L = 1; L <= n ; L++){
            for(int i = 0; i + L - 1 < n; i++){
                int j = i + L -1;
                if(i==j){
                    check[i][j] = true;
                }else if(j == i + 1){
                    check[i][j] = (s.charAt(i)==s.charAt(j));
                }else{
                    check[i][j] = (s.charAt(i)==s.charAt(j) && check[i+1][j-1]);
                }
            }
        }

        int[] dp = new int[n+1];
        dp[n] = 0;
        for(int i = n-1; i >= 0; i--){
            int min = 1_00_000_000;
            for(int k = i; k < n; k++){
                if(!check[i][k]) continue;
                min = Math.min(min, 1 + dp[k+1]);
            }
            dp[i] = min;
        }
        return dp[0] - 1;
    }
}
