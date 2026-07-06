// Length of shortest common supersequence : 
// - max length will be 
// - s1 + s2
// - to make it short .. we will remove the LCS from it.
// - ans = (s1.length + s2.length - (LSC length)).



// - find the LCS dp[][].
// - travel from n,m


class Solution {
    int[][] lcs(String s1 , String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n ; i++){
            for(int j = 1 ; j <= m; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1] , dp[i - 1][j]);
                }
            }
        }

        return dp;
    }
    public String shortestCommonSupersequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = lcs(s1,s2);
        
        int i = n;
        int j = m;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0){
            if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                //add char and move diagonal;
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            }else{
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                if(left > up){
                    //print s2.
                    //move left.
                    sb.append(s2.charAt(j - 1));
                    j--;
                }else{
                    // print s1.
                    // move up
                    sb.append(s1.charAt(i - 1));
                    i--;
                }
            }
        }

        while(i > 0){
            sb.append(s1.charAt(i - 1));
            i--;
        }
        while(j > 0){
            sb.append(s2.charAt(j - 1));
            j--;
        }

        return new String(sb.reverse());
    }
}
