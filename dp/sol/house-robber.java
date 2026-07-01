// ---------------------- recursion ---------------------
// time : 2^n
// space : n (recur stack)

class Solution {
    int fun(int i , int[] nums){
        if(i == 0) return nums[0];

        int p = nums[i] + (i >= 2 ? fun(i-2 , nums) : 0);
        int np = fun(i-1 , nums);

        return Math.max(p , np);
    }
    public int rob(int[] nums) {
        int n = nums.length;
        return fun(n-1 , nums);
    }
}



// ------------------- Memo ------------------------


class Solution {
    int fun(int i , int[] nums, int[] dp){
        if(i == 0) return nums[0];
        if(dp[i] != -1) return dp[i];

        int p = nums[i] + (i >= 2 ? fun(i-2 , nums, dp) : 0);
        int np = fun(i-1 , nums, dp);

        return dp[i] = Math.max(p , np);
    }
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp , -1);
        return fun(n-1 , nums , dp);
    }
}



// ------------------------- Tabulation -----------------------
// time : N
// space : N

// main loop : 
// i : n-1 -> 0 .. recur
// i : 0 -> n-1 .. tab

// base case : 
// i = 0;

// inner for loop : 
// i : 1 -> n-1

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1 ;i < n;i++){
            int p = nums[i] + (i >= 2 ? dp[i-2] : 0);
            int np = dp[i-1];
            dp[i] = Math.max(p , np);
        }
        return dp[n-1];
    }
}




// ----------------------- space-opti ---------------------
// time : N
// space : 1



class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        int a = 0;
        int b = nums[0];
        for(int i = 1 ;i < n;i++){
            int p = nums[i] + (i >= 2 ? a : 0);
            int np = b;
            int c = Math.max(p , np);

            a = b;
            b = c;
        }
        return b;
    }
}


