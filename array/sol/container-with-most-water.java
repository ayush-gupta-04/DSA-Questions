// i have to choose 2 pillars .. such that water is max in between.
// for a particular pillar .. i will look for it's counterpart from right to left... i will seek greedily.
// so it becomes a 2 pointer problem .. moving towards each other.
// for every 2 pillar .. i will calc the max .. and will move the pointer which is on the smaller pillar.


class Solution {
    public int maxArea(int[] nums) {
        int n = nums.length;
        int r = n - 1;
        int l = 0;
        int max = 0;
        while(l < r){
            int gaps = r - l;
            if(nums[l] > nums[r]){
                max = Math.max(max , gaps * nums[r]);
                r--;
            }else{
                max = Math.max(max , gaps * nums[l]);
                l++;
            }
        }

        return max;
    }
}
