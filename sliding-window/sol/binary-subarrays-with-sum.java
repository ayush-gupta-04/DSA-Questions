// ------------ BETTER Approach - using prefix sum --------------------------
// TC -> N
// SC -> N.
// It's a very straight forward prefix sum technique.

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int cnt = 0;
        for(int i = 0;i < n; i++){
            sum += nums[i];
            cnt += map.getOrDefault(sum - goal , 0);
            map.put(sum , map.getOrDefault(sum ,0)+1);
        }
        return cnt;
    }
}



// -------------- OPTIMAL Approch - Sliding Window -----------------------
// TC -> N
// SC -> 1

// when i am at r and sum == goal.
// there are many sub-arrays jo count hona chut jaa rhe hai.
// because we have a constraint that sum should be equal to goal.
// if this would have been sum <= goal .. then koi issue nhi hota.
class Solution {

    // calc number of subarray with sum <= goal.
    int fun(int[] nums , int goal){
        int r = 0;
        int l = 0;
        int n = nums.length;
        int sum = 0;
        int cnt = 0;
        while(r < n){
            sum += nums[r];
            while(l <= r && sum > goal){
                sum -= nums[l];
                l++;
            }

            cnt += (r - l + 1);
            r++;
        }
        return cnt;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        // just calc fun(goal) - fun(goal-1).
        return fun(nums , goal) - fun(nums , goal - 1);
    }
}
