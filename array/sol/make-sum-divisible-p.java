// total = total sum.
// ssum = subarray sum.
// (total - ssum) % p == 0
// -> total % p == ssum % p.

// now to we need to find a sub-array whose sum leaves a remainder of (total) % p.
// for subarray ,let -> pre[r] = a .... pre[l-1] = b.
// now -> 
// -> (a - b) % p = total % p
// -> a%p - b%p = total%p.
// -> a%p - total%p = b%p.
// -> b%p = a%p - total%p....... now here a%p - total%p can be -ve...but we need to avoid it.
// -> b%p = ((a - total) % p + p) % p.

// if we are at an index 'i' .. current mod = (mod + nums[i]) % p.
//                              needed mod = rem = ((a - total) % p + p) % p.


class Solution {
    public int minSubarray(int[] nums, int p) {
        int total = 0;
        int n = nums.length;
        for(int a : nums){
            total = (total + a )%p;
        }

        if(total % p == 0) return 0;

        HashMap<Integer , Integer> map = new HashMap<>();
        int min = (int)1e9;
        int sum = 0;
        map.put(0,-1);

        for(int i = 0; i < n; i++){
            sum = (sum + nums[i]) % p;
            int rem = ((sum - total) % p + p) % p;
            if(map.containsKey(rem)){
                int idx = map.get(rem);
                min = Math.min(min , i - idx);
            }
            map.put(sum , i);
        }
        return min == n ? -1 : min;
    }
}
