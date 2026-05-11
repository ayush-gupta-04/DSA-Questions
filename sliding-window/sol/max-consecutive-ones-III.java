class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int r = 0;
        int l = 0;
        int max = 0;
        while(r < n){
            // dec k if 0 is included.
            k = k + (nums[r] == 1 ? 0 : -1);
            while(l <= r && k < 0 ){
                // inc k if excluded 0
                k = k + (nums[l] == 0 ? 1 : 0);
                l++;
            }
            max = Math.max(max , r - l + 1);
            r++;
        }
        return max;
    }
}
