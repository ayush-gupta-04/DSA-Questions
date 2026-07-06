class Solution {
    boolean fun(int i1 , int i2 , String s , String p){
        if(i1 == 0 && i2 == 0) return true;  // both exhausted
        if(i1 > 0 && i2 == 0) return false;  // p exhausted but .. s is there.
        if(i1 == 0 && i2 > 0){    // s exhausted but .. p is there.
            int i = i2;
            while(i > 0){
                if(p.charAt(i - 1) != '*'){
                    return false;
                }
                i--;
            }
            return true;
        }

        if(s.charAt(i1 - 1) == p.charAt(i2 - 1) || p.charAt(i2 - 1) == '?'){
            return fun(i1 - 1, i2 - 1, s , p);  // matched.
        }

        if(p.charAt(i2 - 1) == '*'){
            boolean left = fun(i1-1, i2 , s , p); // * matches everyone.
            boolean right = fun(i1 , i2-1 , s ,p);  // * as empty
            return left || right;
        }
        return false;
    }
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp  = new boolean[n + 1][m + 1];


        //filled the base case.
        dp[0][0] = true;
        for(int i1 = 1 ;i1 <= n ; i1++){
            dp[i1][0] = false;
        }
        for(int i2 = 1 ; i2 <= m ; i2++){
            int i = i2;
            
            while(i > 0){
                if(p.charAt(i - 1) != '*'){
                    dp[0][i2] = false;
                    break;
                }
                i--;
            }
            if(i == 0){
                //all stars the.
                dp[0][i2] = true;
            }else{
                dp[0][i2] = false;
            }
        }


        //for loop : 
        //i1 = 1 -> n.
        //i2 = 1 -> m.
        for(int i1 = 1 ; i1 <= n ; i1++){
            for(int i2 = 1 ; i2 <= m ; i2++){
                if(s.charAt(i1 - 1) == p.charAt(i2 - 1) || p.charAt(i2 - 1) == '?'){
                    dp[i1][i2] =  dp[i1 - 1][i2 - 1];
                }else if(p.charAt(i2 - 1) == '*'){
                    boolean left = dp[i1-1][i2]; // * matches everyone.
                    boolean right = dp[i1][i2-1];  // * as empty
                    dp[i1][i2] = left || right;
                }else{
                    dp[i1][i2] =  false;
                }
            }
        }
        return dp[n][m];

    }
}
