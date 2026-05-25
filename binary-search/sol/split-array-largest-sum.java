class Solution {
    public int calc(int maxLimit,int[] nums){
        int cnt = 1;
        int sum = 0;
        for(int i = 0;i < nums.length ; i++){
            if(sum + nums[i] <= maxLimit){
                sum += nums[i];
            }
            else{
                sum = nums[i];
                cnt++;
            }           
        }
        return cnt;
    }
    public int splitArray(int[] nums, int k) {
        int max = nums[0];
        int sum = 0;
        for(int a : nums){
            sum += a;
            max = Math.max(max,a);
        }

        //so i have a range.
        int s = max;
        int e = sum;
        while(s <= e){
            int mid = s + (e - s)/2;
            int subArr = calc(mid,nums);
            if(subArr > k){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return s;
    }
}
