// sorting according to the start time .. we couldn't detemine which to remove.
// soriting acc to the end-time .. we can easily determine which to remove.
// if nums[i] dont overlap with prev .. prev = nums[i].
// if nums[i] overlap with prev .. we will hypotetically remove nums[i] (because it will have later end-time)


class Solution {
    public int eraseOverlapIntervals(int[][] nums) {
        Arrays.sort(nums , (x,y) -> Integer.compare(x[1], y[1]));

        int[] prev = nums[0];
        int n = nums.length;
        int cnt = 0;

        for(int i = 1; i < n; i++){
            if(prev[1] <= nums[i][0]){   // don't overlap
                prev = nums[i];
            }else{
                cnt++;   // overlap .. my prev is still the old one.
            }
        }

        return cnt;

    }
}
