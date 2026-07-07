// -------------------------------- THE DP METHODS ------------------------------
// ----------------------- recur -----------------
// time : 
// space : 

class Solution {
    static int fun(int i, int last, int[] nums){
        if(i == nums.length) return 0;
        
        int np = fun(i+1, last , nums);
        int p = 0;
        if(last == -1 || nums[i] > nums[last]){
            p = 1 + fun(i+1 , i , nums);
        }
        return Math.max(p , np);
    }
    static int lis(int arr[]) {
        return fun(0,-1,arr);
    }
}


// ---------------------- Memo -----------------------
// time : 
// space : 

// - notice : 
// - i : 0 -> n  ... (n + 1)
// - last : -1 -> n-1  ... (last is -ve, how do we show it in the dp[][])

// We do coordinate shift for last.
// - last will go from -1 -> n-1  .. no change in this.
// - if last == -1 .. we will store it in index = 0 of dp.
// - if last == 0 .. we will store it in index = 1 of dp.
// ...


// --- The Crux ---
// Whenever we access the jth index of dp[i][j]
// we will ALWAYS access it with a +1.
// we will not right shift any variable itself ... like (last : -1 -> n  ... to ... last : 0 -> n+1)

class Solution {
    static int fun(int i, int last, int[] nums, int[][] dp){
        if(i == nums.length) return 0;
        
        if(dp[i][last + 1] != -1) return dp[i][last + 1];
        
        int np = fun(i+1, last , nums, dp);
        int p = 0;
        if(last == -1 || nums[i] > nums[last]){
            p = 1 + fun(i+1 , i , nums, dp);
        }
        return dp[i][last + 1] = Math.max(p , np);
    }
    static int lis(int nums[]) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        for(int[] a : dp) Arrays.fill(a , -1);
        
        return fun(0, -1, nums, dp);
    }
}




// ---------------------- tab -------------------------
// time : 
// space : 

// main loop : 
// i : 0 -> n
// last : -1 -> n-1


// inner for loop : 
// i : n-1 -> 0
// last : i-1 -> -1    // last is always less then i.

class Solution {
    static int lis(int nums[]) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        
        for(int last = -1 ; last < n; last++){
            dp[n][last + 1] = 0;
        }
        
        for(int i = n-1 ; i >= 0 ; i--){
            for(int last = i-1 ; last >= -1 ; last--){    // last always less then i
                int np = dp[i+1][last+1];
                int p = 0;
                if(last == -1 || nums[i] > nums[last]){
                    p = 1 + dp[i+1][i+1];
                }
                dp[i][last + 1] = Math.max(p , np);
            }
        }
        
        return dp[0][0];
    }
}


// ------------------ sp opt -------------------

class Solution {
    static int lis(int nums[]) {
        int n = nums.length;
        int[] prev = new int[n+1];
        
        for(int last = -1 ; last < n; last++){
            prev[last + 1] = 0;
        }
        
        for(int i = n-1 ; i >= 0 ; i--){
            int[] curr = new int[n+1];
            for(int last = i-1 ; last >= -1 ; last--){
                int np = prev[last+1];
                int p = 0;
                if(last == -1 || nums[i] > nums[last]){
                    p = 1 + prev[i+1];
                }
                curr[last + 1] = Math.max(p , np);
            }
            prev = curr;
        }
        
        return prev[0];
    }
}



// ---------------------------- THE N x N METHOD --------------------------------
// ----------- length of LIS ------------
// time : N x N
// space : N x N

class Solution {
    static int lis(int nums[]) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        
        int maxLen = 1;
        for(int i = 0;i < n ;i++){
            for(int j = 0; j < i ; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen , dp[i]);
        }
        
        return maxLen;
    }
}


// ------ print LIS ---------
// time : N x N
// space : N x N

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0 ; i < n ; i++){
            parent[i] = i;
        }
        int max = 1;
        int lastIndex = 0;

        for(int ind = 0 ; ind < n ; ind++){
            for(int i = 0 ; i < ind ; i++){
                if(nums[i] < nums[ind] && dp[i] + 1 > dp[ind]){
                    dp[ind] = dp[i] + 1;
                    parent[ind] = i;
                }
            }


            //we need lastIndex to start from.
            if(dp[ind] > max){
                max = dp[ind];
                lastIndex = ind;
            }
        }


        List<Integer> list = new ArrayList<>();
        while(parent[lastIndex] != lastIndex){
            //add lastIndex to ans;
            list.add(0,nums[lastIndex]);
            lastIndex = parent[lastIndex];
        }
        list.add(0,nums[lastIndex]);
        System.out.print(list);
        return max;


    }
}


// ----------------------------------------- THE BINARY SEARCH METHOD ----------------------------------------
// time : N x logN
// space : N

class Solution {
    static int lowerBound(List<Integer> nums, int k){
        int s = 0;
        int e = nums.size()-1;
        
        while(s <= e){
            int m = s + (e - s)/2;
            if(k > nums.get(m)){
                s = m + 1;
            }else{
                e = m -1;
            }
        }
        return s;
    }
    static int lis(int nums[]) {
        List<Integer> list = new ArrayList<>();
        
        for(int a : nums){
            // if list is empty or a can be inserted in the last.
            // push a to the last.
            if(list.isEmpty() || a > list.getLast()){
                list.add(a);
            }else{
                int lb = lowerBound(list , a);
                list.set(lb , a);
            }
        }
        return list.size();
    }
}
