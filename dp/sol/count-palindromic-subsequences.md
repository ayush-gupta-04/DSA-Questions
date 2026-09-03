## Dynamic Programming via Inclusion-Exclusion (Optimal)

Instead of generating subsequences from scratch, we solve the problem for smaller substrings and expand outward.

Let $DP[i][j]$ represent the total number of palindromic subsequences in the substring $s[i \dots j]$.

When looking at the substring $s[i \dots j]$, consider two smaller overlapping windows:

* $s[i \dots j-1]$ (excludes right endpoint $s[j]$)
* $s[i+1 \dots j]$ (excludes left endpoint $s[i]$)

Both windows contain the middle segment $s[i+1 \dots j-1]$. By the **Principle of Inclusion-Exclusion**, combining these two windows double-counts the middle segment:

$$\text{Base Count} = DP[i+1][j] + DP[i][j-1] - DP[i+1][j-1]$$

Now check whether the boundary characters $s[i]$ and $s[j]$ can form new palindromes together:

**Case 1: $s[i] \neq s[j]$**
The two endpoints are different, so no palindrome can start with $s[i]$ and end with $s[j]$. No new palindromes can be formed using both endpoints:

$$DP[i][j] = DP[i+1][j] + DP[i][j-1] - DP[i+1][j-1]$$

**Case 2: $s[i] == s[j]$**
Because the ends match, they can pair up:

1. Every existing palindromic subsequence inside the inner segment $s[i+1 \dots j-1]$ can be wrapped by $s[i]$ and $s[j]$ to create a new palindrome (contributing $DP[i+1][j-1]$ new palindromes).
2. The pair $s[i]$ and $s[j]$ by themselves forms a palindrome of length 2 (contributing $+1$).

Adding these $(DP[i+1][j-1] + 1)$ new palindromes cancels out the $-DP[i+1][j-1]$ term:

$$DP[i][j] = DP[i+1][j] + DP[i][j-1] + 1$$

---
- time : N*N
- space : N*N

``` java

class Solution {
    int countPS(String s) {
        // Your code here
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for(int L = 1;L <= n; L++){
            for(int i = 0 ;i + L - 1 < n; i++){
                int j = i + L - 1;
                char ch_i = s.charAt(i);
                char ch_j = s.charAt(j);
                
                if(i==j) dp[i][j] = 1;
                else if(i+1 == j) dp[i][j] = (ch_i==ch_j ? 3 : 2);
                else{
                    if(ch_i != ch_j){
                        dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                    }else{
                        dp[i][j] = dp[i+1][j] + dp[i][j-1] + 1;
                    }
                }
            }
        }
        
        
        return dp[0][n-1];
    }
}
```
