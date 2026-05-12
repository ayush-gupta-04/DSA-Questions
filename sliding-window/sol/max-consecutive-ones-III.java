// --------------------- Better Approach : Sliding Window ----------------------------
// TC - O(2N) -> each element is visited 2 times .. once by the right and once by the left.
// SC - O(1)
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


// --------------------- Optimal Approach : Sliding Window + (no inner while loop) ----------------------------
// TC - O(N) -> each element is visited 1 time .. once by the right or by the left.
// SC - O(1)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int r = 0;
        int l = 0;
        int max = 0;
        while(r < n){
            // dec k if 0 is included.
            k = k + (nums[r] == 1 ? 0 : -1);
            if(l <= r && k < 0 ){
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
