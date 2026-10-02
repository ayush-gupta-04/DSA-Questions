// ---------------- Better Approach -------------------

// time : 3N
// space : 2N



class Solution {
    public int trap(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suff = new int[n];

        pre[0] = nums[0];
        suff[n-1] = nums[n-1];
        for(int i = 1;i < n;i++){
            pre[i] = Math.max(nums[i], pre[i-1]);
        }
        for(int i = n-2 ;i >= 0 ;i--){
            suff[i] = Math.max(nums[i], suff[i+1]);
        }


        int ans = 0;
        for(int i = 0;i < n;i++){
            int left = pre[i];
            int right = suff[i];
            int curr = nums[i];

            if(curr < Math.min(left,right)){
                ans += Math.min(left,right) - curr;
            }
        }
        return ans;
    }
}




// ------------------- Optimal Approach --------------------
// time : N
// space : 1


class Solution {
    public int trap(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;

        int lMax = 0;
        int rMax = 0;
        int total = 0;
        while(l <= r){
            if(nums[l] < nums[r]){
                if(lMax > nums[l]){
                    total += lMax - nums[l];
                }
                lMax = Math.max(lMax, nums[l]);
                l++;
            }else{
                if(rMax > nums[r]){
                    total += rMax - nums[r];
                }
                rMax = Math.max(rMax, nums[r]);
                r--;
            }
        }
        return total;
    }
}
