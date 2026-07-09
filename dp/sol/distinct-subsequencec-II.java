// consider : abab
//   dp[0] = 1
// a dp[1] = 2  = {"", a}
// b dp[2] = 4  = {"", a, b, ab}
// a dp[3] = 7  = {"", a, b, ab, |a|, aa, ba, aba}.
// b dp[4] = 12 = {"", a, b, ab, aa, ba, aba, |b|, |ab|, bb, aba, aab, bab, abab}

// dp[4]
// we will have b, ab repeating.
// dp[1] had {"", a} .. and we added "b" in this in dp[2].
// dp[4] still had this {"", a} and also {b, ab}
// now if we again add "b" to {"", a} .. {b, ab} will repeat.

// solution : 
// dp[i] = dp[i-1]*2 - dp[(last index of ch) - 1]




class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int MOD = 1_000_000_007;

        int[] dp = new int[n+1];
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dp[0] = 1; 
        for(int i = 1; i <= n; i++){
            char ch = s.charAt(i-1);

            dp[i] = (dp[i-1]*2)%MOD;

            if(last[ch-'a'] != -1){
                dp[i] = (dp[i] - dp[last[ch-'a'] - 1] + MOD)%MOD;
            }

            last[ch-'a'] = i;
        }

        return (dp[n]-1 + MOD)%MOD;
    }
}
