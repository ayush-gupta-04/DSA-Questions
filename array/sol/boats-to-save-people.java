// for minimum boats .. we will try 
//                      1. to take 2 people with us at a time.
//                      2. weight of the people must be <= limit.

// it looks like 2 sum .. 2 pointers .. running opposite.
class Solution {
    public int numRescueBoats(int[] nums, int limit) {
        Arrays.sort(nums);
        int n = nums.length;
        int cnt = 0;
        int s = 0;
        int e = n - 1;
        while(s <= e){
            if(nums[s] + nums[e] <= limit){
                cnt++;
                s++;
                e--;
            }else{
                // person at the 'e' will require a whole ship.
                cnt++;
                e--;
            }
        }
        return cnt;
    }
}
