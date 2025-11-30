// We need to find l , r such that (prefixSum[r] - prefixSum[l-1]) % k == 0;
// A property ->  (a +- b) % k == 0.....then (a % k  +-  b % k).
// therefore .... prefixSum[r] % k = prefixSum[l-1] % k.

// in general -> 7 % 5 = 2 ... since ( 7 = 5*1 + 2 )
//            -> -7 % 5 = 3 .. since ( -7 = 5*-2 + 3 )

// but in java -> 7 % 5 = 2;
//             -> -7 % 5 = 2.....but we don't want 2 here...we want 3.


// solution -> premod = (premod + A[i]%k + k) % k.
// the +k ensure that -ve are handled properly.

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] map = new int[k];
        int preMod = 0;
        int cnt = 0;

        // if we find a subarray with sum nk .. it's mod will be 0.
        // we should have a 0 with cnt = 1 ... which is 0 -> 1 in map.
        map[0] = 1;
        for(int i = 0; i < nums.length ; i++){
            preMod = (preMod + nums[i] % k + k)%k;
            cnt += map[preMod];
            map[preMod]++;
        }
        return cnt;
    }
}
