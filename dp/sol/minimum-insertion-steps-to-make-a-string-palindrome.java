// we have string s
// s will have multiple subsequence which are palindromes.
// If we leave the palindrome as it is .. 
//    and add the remaining char in such a way to make the whole string palindrome.


class Solution {
    String reverse(String s){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0 ; i < s.length() ; i++){
            sb.insert(0,s.charAt(i));
        }
        return new String(sb);
    }

    public int minInsertions(String S1) {
       String S2 = reverse(S1); 
       int n = S1.length();
       int m = S2.length();


       int[][] dp = new int[n + 1][m + 1];

       for(int i1 = 1 ; i1 <= n ; i1++){
           for(int i2 = 1; i2 <= m ; i2++){
               if(S1.charAt(i1 - 1) == S2.charAt(i2 - 1)){
                   dp[i1][i2] =  1 + dp[i1 - 1][i2 - 1];
                   continue;
               }
               dp[i1][i2] = Math.max(dp[i1 - 1][i2] , dp[i1][i2 - 1]);
           }
       }

       return n - dp[n][m];
    }
}
