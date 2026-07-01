// ---------------------- recursion ---------------------
// time : 2^n
// space : n (recursive stack)


class Solution {
    public int climbStairs(int n) {
        if(n <= 1) return 1;
        return climbStairs(n-1) + climbStairs(n-2);
    }
}



// ------------------- Tabulation ----------------------
// time : N
// space  N


// main loop
// n : n -> 0 .. in recur
// n : 0 -> n .. in tabulation

// base case : 
// n = 0
// n = 1

// inner for : 
// n : 2 -> n.

class Solution {
    public int climbStairs(int N) {
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int n = 2 ; n <= N ; n++){
            dp[n] = dp[n-1] + dp[n-2];
        }
        return dp[N];
    }
}





// ------------------ space optimization ------------------
// time : N
// space : 1


// main loop
// n : n -> 0 .. in recur
// n : 0 -> n .. in tabulation

// base case : 
// n = 0
// n = 1

// inner for : 
// n : 2 -> n.

class Solution {
    public int climbStairs(int N) {
        int a = 1;
        int b = 1;
        for(int n = 2 ; n <= N ; n++){
            int c = a + b;
            b = a;
            a = c;
        }
        return a;  // last a was assigned (a+b);
    }
}
