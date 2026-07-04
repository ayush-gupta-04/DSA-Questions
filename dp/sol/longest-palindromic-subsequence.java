// find LCS of s and rev(s)
// because the longest palindrome in s will be same as in rev(s).

class Solution {
    String reverse(String s){
        StringBuilder sb = new StringBuilder(s);
        return new String(sb.reverse());
    }
    public int longestPalindromeSubseq(String s1) {
        int n = s1.length();
        String s2 = reverse(s1);

        int[][] dp = new int[n + 1][n + 1];

        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ; i2 <= n ; i2++){
                if(s1.charAt(i1 - 1) == s2.charAt(i2 - 1)){
                    dp[i1][i2] = 1 + dp[i1 - 1][i2 - 1];
                }else{
                    dp[i1][i2] = Math.max(dp[i1 - 1][i2] , dp[i1][i2 - 1]);
                }
            }
        }

        return dp[n][n];
    }
}
