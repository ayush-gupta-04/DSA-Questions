// intution : It would be very easy to count the subarray if only upper bound is given .
//            i.e count the no. of subarray having max <= x.

// Then i can easily find the ans for [left , right] .. = ans(right) - ans(left - 1);

class Solution {
    int ans(int[] nums , int x){
        int l = 0;
        int r = 0;
        int n = nums.length;
        int max = nums[0];
        int cnt = 0;
        while(r < n){
            max = Math.max(max , nums[r]);
            if(max > x){
                r++;
                l = r;
                if(r < n) max = nums[r];
                continue;
            }

            // for this subarray .. the max is <= x .. count every subarray ending here at r.
            cnt += (r - l + 1);
            r++;
        }
        return cnt;
    }
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return ans(nums , right) - ans(nums , left-1);
    }
}
