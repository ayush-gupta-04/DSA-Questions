class Solution {
    int fun(int i1, int i2 , String s1 ,String s2){
        if(i2 == 0) return 1;
        if(i1 == 0) return 0;

        if(s1.charAt(i1 - 1) == s2.charAt(i2 - 1)){
            return fun(i1 - 1, i2 - 1,s1,s2) + fun(i1 - 1,i2 ,s1,s2);
        }else{
            return fun(i1 - 1,i2 ,s1,s2);
        }
    }
    public int numDistinct(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int i1 = 0 ; i1 <= n; i1++){
            dp[i1][0] = 1;
        }
        
        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ; i2 <= m ; i2++){
                if(s1.charAt(i1 - 1) == s2.charAt(i2 - 1)){
                    dp[i1][i2] =  dp[i1 - 1][i2 - 1] + dp[i1 - 1][i2];
                }else{
                    dp[i1][i2] = dp[i1 - 1][i2];
                }
            }
        }

        return dp[n][m];
    }
}
