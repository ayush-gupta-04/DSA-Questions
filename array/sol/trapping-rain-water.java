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
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }
}
