// ---------------- Optimal Approach : Only Positives | Positives + Zeros ------------------------
// time : 2*N
// space : 1

// Here we need to count subarray with a property K
// we can change the equality and count with sliding window approach.
class Solution {
    int fun(int[] nums, int k){
        int n = nums.length;
        int cnt = 0;
        int sum = 0;
        int r = 0;
        int l = 0;
        while(r < n){
            sum += nums[r];
            while(l<=r && sum > k){
                sum -= nums[l];
                l++;
            }
            
            cnt += (r - l + 1);
            r++;
        }
        return cnt;
    }
    public int subarraySum(int[] nums, int k) {
        return fun(nums,k) - fun(nums,k-1);
    }
}


// --------------------- Optimal Approach : Every Integer -------------------------
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
