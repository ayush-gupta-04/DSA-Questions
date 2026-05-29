// If i have a subarray with no 0.
// case 1 : even -ve numbers.
//   - ans = product of all.
// case 2 : odd -ve numbers.
//   - we will try to exclude every number from the total product.
//   - either the prefix Product or the suffix product.


class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int pre = 1;
        int suff = 1;
        int n = nums.length;
        for(int i =0 ;i < n;i++){
            pre = pre * nums[i];
            suff = suff * nums[n-i-1];

            max = Math.max(max , Math.max(pre,suff));

            if(pre == 0) pre = 1;
            if(suff == 0) suff = 1;
        }
        return max;
    }
}
