// Same question as Maximum Meetings in 1-room


class Solution {
    public int eraseOverlapIntervals(int[][] nums) {
        Arrays.sort(nums , (x,y) -> Integer.compare(x[1], y[1]));

        int[] prev = nums[0];
        int n = nums.length;
        int cnt = 1;

        for(int i = 1; i < n; i++){
            if(prev[1] <= nums[i][0]){   // don't overlap
                prev = nums[i];
                cnt++; 
            }
        }

        return nums.length - cnt;

    }
}
