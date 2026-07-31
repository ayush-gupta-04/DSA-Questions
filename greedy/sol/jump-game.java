class Solution {
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        int n = nums.length;
        int i = 0;
        while(i < n){
            maxJump = Math.max(maxJump , i + nums[i]);
            if(i != n-1 && maxJump == i){    // if i can max jump to here only .. i cannot go forward.
                return false;
            }
            i++;
        }
        return true;
    }
}
