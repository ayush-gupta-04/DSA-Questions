// if i am at 'i' .. i dont' want to find the i-1th index in the map..
//      bacause then my subarray would be [i]..and it will be only 1 ele.
// i will make sure to add sum till i-1th index when i am at ith index to avoid 
// i-1th index to be present in the map if i am at ith index.

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer , Integer> map = new HashMap<>();
        int sum = 0;
        int cnt = 0;
        for(int i = 0;  i < nums.length ; i++){
            int curr = (sum + nums[i]) % k;
            if(map.containsKey(curr)){
                return true;
            }
            map.put(sum, 1);
            sum = (sum + nums[i])%k;
        }
        return false;
    }
}
