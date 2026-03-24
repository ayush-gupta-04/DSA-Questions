// ------------------ BETTER Appraoch - using prefix sum -----------------------
// TC -> N
// SC -> N
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int cnt = 0;
        int n = nums.length;
        int odd = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int a : nums){
            odd = odd + (a%2 == 0 ? 0 : 1);
            cnt += map.getOrDefault(odd - k , 0);
            map.put(odd , map.getOrDefault(odd , 0) + 1);
        }
        return cnt;
    }
}


// -------------- OPTIMAL Approach : Sliding Window ------------------------
// TC -> N
// SC -> 1
// we cannot count number of windows with exactly k odd numbers.
// because if we are at 'r' .. and odd <= k.
// then there will be many windows that we will miss because we can't shrink the window further left.
// therefore it's more easy to count windows with odd <= k.

class Solution {
    int fun(int[] nums, int k){
        int r = 0;
        int l = 0;
        int cnt = 0;
        int n = nums.length;
        int odd = 0;
        while(r < n){
            odd += (nums[r]%2 == 0 ? 0 : 1);
            while(l<=r && odd > k){
                odd -= (nums[l]%2 == 0 ? 0 : 1);
                l++;
            }
            cnt += (r - l + 1);
            r++;
        }
        return cnt;
    }
    public int numberOfSubarrays(int[] nums, int k) {
        return fun(nums , k ) - fun(nums , k - 1);
    }
}
