// ------------- Brute Force : 2 loop -----------
// time : N*N
// space : 1
class Solution {
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; 
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }
}





// -------------- Optimal : kadane ------------------

// time : N
// space : 1

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = nums[0];
        for(int i = 0;i < n;i++){
            sum += nums[i];
            max = Math.max(sum,max);
            if(sum < 0){
                sum = 0;
            }
        }
        return max;
    }
}


