class Solution {
    public int calculateDays(int[] nums,int capacity){
        int sum = 0;
        int days = 1;
        for(int i = 0;i < nums.length;i++){
            if(sum + nums[i] <= capacity){
                sum += nums[i];
            }else{
                sum = nums[i];
                days++;
            }
        }
        return days;
    }
    public int shipWithinDays(int[] nums, int maxDays) {
        int min = nums[0];
        int max = 0;
        for(int a : nums){
            max = max + a;
            min = Math.max(min,a);
        }
        int s = min;
        int e = max;
        while(s <= e){
            int capacity = s + (e-s)/2;
            int days = calculateDays(nums,capacity);
            if(days > maxDays){
                s = capacity + 1;
            }else{
                e = capacity - 1;
            } 
        }
        return s;
    }
}
