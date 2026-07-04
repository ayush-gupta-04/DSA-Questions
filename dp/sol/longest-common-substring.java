// ----------------- brute - force -------------------
// - generate all substring of s1 and s2.
// - check which are equal.

// -------------- better - approach ---------------------
// - generate all substring of s1 and s2.
// - store all substring of s2 in trie.
// - for every substring of s1 .. check if it is in the trie or not.


// ----------------- optimal approach ----------------
// time : N x M
// space : N x M

class Solution {
    public int longCommSubstr(String s1, String s2) {
        // code here
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        int max = 0;
        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ;i2 <= m; i2++){
                if(s1.charAt(i1-1) == s2.charAt(i2-1)){
                    // increase the last substring by 1.
                    dp[i1][i2] = 1 + dp[i1-1][i2-1];
                }
                else{
                    // the last substring is broken.
                    // mark this 0;
                    dp[i1][i2] = 0;
                }
                max = Math.max(max , dp[i1][i2]);
            }
        }
        return max;
    }
}
