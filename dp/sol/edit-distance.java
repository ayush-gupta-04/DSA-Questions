// --------------- recur -----------------
// time : 3 ^(N + M)
// space : N + M


class Solution {
    int fun(int i1 , int i2 , String S1 , String S2){
        if(i1 == 0) return i2; // steps to convert "" to S2 .. insert all
        if(i2 == 0) return i1; // steps to convert S1 to "" .. delete all

        if(S1.charAt(i1 - 1) == S2.charAt(i2 - 1)){
            return fun(i1 - 1, i2 - 1, S1 , S2);
        }
        int insert = fun(i1 , i2-1 , S1, S2);
        int delete = fun(i1 - 1 , i2,S1,S2);
        int replace = fun(i1 - 1 , i2 - 1 , S1,S2);
        return 1 + Math.min(insert, Math.min(delete , replace));
    }
    public int minDistance(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();
        return fun(n,m,S1,S2);
    }
}



// -------------------- tab -------------------------
// time : N x M
// space : N x M


class Solution {
    public int minDistance(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();
        
        int[][] dp = new int[n + 1][m + 1];

        //base case: 
        for(int i2 = 0; i2 <= m ; i2++){
            dp[0][i2] = i2;
        }
        for(int i1 = 0; i1 <= n ; i1++){
            dp[i1][0] = i1;
        }

        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ; i2 <= m ; i2++){
                if(S1.charAt(i1 - 1) == S2.charAt(i2 - 1)){
                    dp[i1][i2] = dp[i1- 1][i2 - 1];
                }else{
                    int d = dp[i1- 1][i2];
                    int r = dp[i1 - 1][i2 - 1];
                    int i = dp[i1][i2 - 1];
                    dp[i1][i2] = 1 + Math.min(d , Math.min(r , i));
                }
            }
        }

        return dp[n][m];
    }
}
