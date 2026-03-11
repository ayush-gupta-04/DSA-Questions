// ---------------------------------- APPROACH-1 : Optimal  -------------------------------------------
// TC - O(31.N)
// SC - O(1)
class Solution {
    public int singleNumber(int[] nums) {
        int num = 0;
        for(int i = 0;i < 32 ; i++){
            int mask = (1 << i);
            int cnt = 0;
            for(int a : nums){
                if((a & (mask)) != 0){
                    cnt++;
                }
            }
            if(cnt%3 == 1){
                num = num | (mask);
            }
        }
        return num;
    }
}

// ----------------------------------- APPROACH-2 : Optimal -----------------------------------------------
// TC - O(NlogN)
// SC - O(1)

class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 1; i < n; i += 3){
            if(nums[i-1] != nums[i]){
                return nums[i-1];
            }
        }
        return nums[n-1];
    }
}
