// ----------------------- recur -----------------------
// time : 2^(N + M)
// space : N + M

class Solution {
    static int fun(int i1, int i2, String s1, String s2){
        if(i1 == 0 || i2 == 0) return 0;
        
        if(s1.charAt(i1-1) == s2.charAt(i2-1)){
            return 1 + fun(i1-1 , i2-1 , s1,s2);
        }
        int left = fun(i1, i2 - 1 , s1, s2);
        int up = fun(i1-1, i2 , s1, s2);
        return Math.max(left , up);
    }
    static int lcs(String s1, String s2) {
        return fun(n,m,s1,s2);
    }
}



// -------------------------- tab --------------------
// time : N x M
// space : (N x M)

class Solution {
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ;i2 <= m; i2++){
                if(s1.charAt(i1-1) == s2.charAt(i2-1)){
                    dp[i1][i2] = 1 + dp[i1-1][i2-1];
                    continue;
                }
                int left = dp[i1][i2-1];
                int up = dp[i1-1][i2];
                dp[i1][i2] = Math.max(left , up);
            }
        }
        return dp[n][m];
    }
}



// ----------------- print LCS --------------------
class Solution {
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ;i2 <= m; i2++){
                if(s1.charAt(i1-1) == s2.charAt(i2-1)){
                    dp[i1][i2] = 1 + dp[i1-1][i2-1];
                    continue;
                }
                int left = dp[i1][i2-1];
                int up = dp[i1-1][i2];
                dp[i1][i2] = Math.max(left , up);
            }
        }
        
        //printing the LCS.
       int i1 = n;
       int i2 = m;
       List<Character> ans = new ArrayList<>();
       while(i1 > 0 && i2 > 0){
           if(s1.charAt(i1 - 1) == s2.charAt(i2 - 1)){
              ans.add(s1.charAt(i1 - 1));
              i1--;
              i2--;
           }else{ 
               if(dp[i1 - 1][i2] > dp[i1][i2 - 1]){
                   i1--;
               }else{
                   i2--;
               }
           }
       }

        Collections.reverse(ans);
    }
}

// ------------------------ sp opti ------------------
// time : N x M
// space : M

class Solution {
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        int[] dp = new int[m + 1];
        
        for(int i1 = 1 ; i1 <= n ; i1++){
            int[] curr = new int[m + 1];
            for(int i2 = 1 ;i2 <= m; i2++){
                if(s1.charAt(i1-1) == s2.charAt(i2-1)){
                    curr[i2] = 1 + dp[i2-1];   // use prev row.
                    continue;
                }
                int left = curr[i2-1];  // use curr row
                int up = dp[i2];       // use prev row.
                curr[i2] = Math.max(left , up);
            }
            dp = curr;
        }
        return dp[m];
    }
}
