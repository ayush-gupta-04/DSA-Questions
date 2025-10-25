// this method won't be memoised .. since i vis is my state and it is an array.
// we have to convert vis to a mask.


class Solution {
    int fun(int i , int lz, int mask, int n , Integer[][][] dp){
        if(i == n) return 1;
        if(dp[i][lz][mask] != null) return dp[i][lz][mask];
        int ans = 0;
        for(int dig = 0 ; dig < 10; dig++){
            if(((mask >> dig)&1) == 1) continue;


            // if lz is 0 --> we have to track the vis.
            // if lz is 1 --> we have to only track non-0 number.
            if(lz == 0 || dig != 0){
                mask = mask | (1 << dig);
            }
            ans = ans + fun(i + 1, (lz == 1 && dig == 0) ? 1 : 0 , mask , n,dp);
            if(lz == 0 || dig != 0) {
                mask = (mask & (~(1 << dig)));
            }
        }
        return dp[i][lz][mask] = ans;
    }
    public int countNumbersWithUniqueDigits(int n) {
        Integer[][][] dp = new Integer[n][2][1 << 10]; 
        return fun(0 , 1,0 , n ,dp);
    }
}
