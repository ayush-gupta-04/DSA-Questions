class Solution {

    int find(int[] nums,int day,int k){

        int bouquets = 0;
        int cnt = 0;

        for(int i = 0;i < nums.length;i++){

            if(nums[i] > day){
                cnt = 0;
                continue;
            }

            cnt++;

            if(cnt == k){
                bouquets++;
                cnt = 0;
            }
        }

        return bouquets;
    }

    public int minDays(int[] nums,int m,int k) {

        int len = nums.length;

        if((long)m * k > len) return -1;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for(int val : nums){
            low = Math.min(low,val);
            high = Math.max(high,val);
        }

        while(low <= high){

            int mid = low + (high - low) / 2;

            int made = find(nums,mid,k);

            if(made < m){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return low;
    }
}
