// we can observer here that the ans is bounded in the range [0 , max distance].
// max distance = max(nums) - min(nums);
// we must sort the array if we want to calc the distance efficiently.

// my ans would lie between : 0 ... D.
// for a 'd' : 
//    -> how many pairs would have dist <= d....let this be cnt.
//    -> as 'd' inc .. cnt inc.
//    -> if( k > cnt ) -> inc the d.
//    -> else dec the d.

// the solution space ..  0 , 1 , 2 , 3 , 4 . . . - , - , - , - , D.
//                        f   f   f   f   f       t   t   t   t   t.

// f -> k > cnt.
// t -> k <= cnt.


class Solution {
    // this is a sliding window approach to count the pairs.
    private int countPairs(int[] nums, int D) {
        int n = nums.length;
        int count = 0;
        int j = 1;

        for (int i = 0; i < n; i++) {
            while (j < n && nums[j] - nums[i] <= D) {
                j++;
            }
            // All indices [i+1 .. j-1] form valid pairs with i
            count += j - i - 1;
        }

        return count;
    }
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int s = 0;
        int e = Math.abs(nums[0] - nums[n-1]);

        while(s <= e){
            int m = s + (e-s)/2;
            int cnt = countPairs(nums , m);
            if(k > cnt){
                s = m + 1;
            }else{
                e = m - 1;
            }
        }
        return s;
    }
}
