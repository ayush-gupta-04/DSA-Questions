// -------------------- --------Brute-Force -----------------------
// Time : (N*(2^N))
//   - for every 2^N partition we are checking for palindrome.
// Space : N + N*(2^N)
//  - N : recursion
//  - N*(2^N) : there can be 2^N palindromes each of length N when combined at worst.

class Solution {
    boolean isPalindrome(String str , int i , int j){
        while(i < j){
            if(str.charAt(i) == str.charAt(j)){
                i++;
                j--;
            }
            else return false;
        }
        return true;
    }
    void fun(String s , int i, List<String> list ,List<List<String>> ans){
        if(i == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int k = i ; k < s.length() ; k++){
            if(!isPalindrome(s,i,k)) continue;

            list.add(s.substring(i , k+1));
            fun(s,k+1,list,ans);
            list.removeLast();
        }
        return;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        fun(s,0,list,ans);
        return ans;
    }   
}



// ------------------------------ Optimal Approach: using DP to palindromic precalculation ------------------
// Time : 2^N + N*N
//    - 2^N : for all palindromes.
//    - N*N : for dp array.
// Space : N + N*(2^N) + N*N
//  - N : recursion
//  - N*(2^N) : there can be 2^N palindromes each of length N when combined at worst.
//  - N*N : for dp array.

class Solution {
    List<List<String>> ans = new ArrayList<>();

    void fun(String s , int i, List<String> list,boolean[][] dp){
        if(i == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int k = i ; k < s.length() ; k++){
            if(!dp[i][k]) continue;  // checks for palindromw.

            list.add(s.substring(i , k+1));
            fun(s,k+1,list,dp);
            list.removeLast();
        }
        return;
    }
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<String> list = new ArrayList<>();


        // dp array precomputes for palindrome.
        boolean[][] dp = new boolean[n][n];
        for(int L = 1 ;L <= n; L++){
            for(int i = 0;i + L -1 < n ;i++){
                int j = i + L -1;
                if(j==i){
                    dp[i][j] = true;
                }else if(j == i + 1){
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }else{
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
                }
            }
        }
        fun(s,0,list,dp);
        return ans;
    }   
}
