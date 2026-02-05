// TC - O(N).
// SC - O(1).

// Intuition. 
// maximum of a circular subarray is either the max Subarray(not circular) ... or 
// ( total - the min subarray )(it will be circular)



class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int tot = 0;
        int miSum = 0;
        int maSum = 0;
        int mi = 1e9;
        int ma = -1e9;
        int n = nums.size();
        for(int i = 0; i < n ; i++){
            tot += nums[i];
            miSum += nums[i];
            maSum += nums[i];
            mi = min(mi,miSum);
            ma = max(ma,maSum);
            if(miSum > 0) miSum = 0;
            if(maSum < 0) maSum = 0;
        }
        if(tot - mi == 0)return ma;
        return max(ma, tot - mi);
    }
};
