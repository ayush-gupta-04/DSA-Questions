class Solution {
    public long calculateHours(int[] nums, int m){
        long sum = 0;
        for(int b : nums){
            if(b%m == 0){
                sum = sum + (b/m);
            }else{
                sum = sum + (b/m) + 1;
            }
        }
        return sum;
    }
    public int minEatingSpeed(int[] nums, int h) {
        int s = 1;
        int e = Integer.MIN_VALUE;
        for(int a : nums) e = Math.max(e , a);

        while(s <= e){
            int mid = s + (e - s)/2;
            long hours = calculateHours(nums,mid);
            if(hours > h){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return s;
    }
}
