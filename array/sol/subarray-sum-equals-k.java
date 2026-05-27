// time : N
// space : N

// Since we have to count subarray with a property K.
// we can change the condition to <= K ... and apply sliding window.
// BUT BUT BUT .. here we can't do anything 
// because we have -ve here.
// we have to use prefix sum techniques.

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(0,1);   // what if sum = 5 and k = 5 ... we will look for 0.
        int sum = 0;
        int cnt = 0;
        for(int i = 0; i < nums.length ; i++){
            sum += nums[i];
            int rem = sum - k;
            cnt += map.getOrDefault(rem , 0);
            map.put(sum , map.getOrDefault(sum , 0) + 1);
        }
        return cnt;
    }
}
