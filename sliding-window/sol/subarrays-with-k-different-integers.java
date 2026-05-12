// here we need to count the subarray with a property P.
// here is how we solve for exactly P case.
class Solution {

    // finds number of subarrays with different integers <= K.
    int fun(int[] nums , int k){
        int r = 0;
        int l = 0;
        int cnt = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        while(r < n){
            map.put(nums[r] , map.getOrDefault(nums[r],0)+1);
            while(l<=r && map.size() > k){
                map.put(nums[l] , map.get(nums[l])-1);
                if(map.get(nums[l]) == 0) map.remove(nums[l]);
                l++;
            }
            cnt += (r - l + 1);
            r++;
        }
        return cnt;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        return fun(nums , k) - fun(nums , k-1);
    }
}
