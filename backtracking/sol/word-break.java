// ----------- Brute Force : Pure Backtracking (TLE) ------------------
// Issues : overlapping subproblem.

// Time : 2^N 
//   - 2^N -> we end up explaoring all the possible partitions of the string.
// Space : N + L*W
//   - N : Max depth = length of the string
//   - L*W : L is size of wordDict, W is avg. size of word in wordDict

class Solution {
    boolean fun(String s , int i, HashSet<String> set){
        if(i == s.length()) return true;

        for(int k = i ; k < s.length() ; k++){
            String sub = s.substring(i,k+1);
            if(!set.contains(sub)) continue;

            if(fun(s , k + 1 , set)) return true;
        }
        return false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        return fun(s,0,set);
    }
}




// --------------------------- Better Approach : Top-Down DP  --------------------------

// Time : N*N*N 
//   - N -> There are N states. (i -> 0 to N).
//   - N -> There is a for loop inside every call.
//   - N -> s.substring() takes N time.
// Space : N + L*W
//   - L*W : L is size of wordDict, W is avg. size of word in wordDict

class Solution {
    boolean fun(String s , int i, HashSet<String> set,Boolean[] dp){
        if(i == s.length()) return true;

        if(dp[i] != null) return dp[i];

        for(int k = i ; k < s.length() ; k++){
            String sub = s.substring(i,k+1);
            if(!set.contains(sub)) continue;

            if(fun(s , k + 1 , set,dp)) return dp[i] = true;
        }
       
        return dp[i] = false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length()];     // use Boolean (Wrapper class)
        return fun(s,0,set,dp);
    }
}






// ------------------------------Optimal Approach : Bottom-Up DP --------------------------

// Time : N*N*N 
//   - N^2 -> 2 loops
//   - N -> s.substring() takes N time.
// Space : N + L*W
//   - N : for dp array
//   - L*W : L is size of wordDict, W is avg. size of word in wordDict

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        for(int i = n-1 ; i >= 0 ;i--){
            boolean ans = false;
            for(int k = i ; k < s.length() ; k++){
                String sub = s.substring(i,k+1);
                if(!set.contains(sub)) continue;
                if(dp[k+1]){
                    ans = true;
                    break;
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }
}
