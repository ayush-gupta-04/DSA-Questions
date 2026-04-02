// let's run this on [10,20,2,30]
// It is ... inf   inf
// num = 10 .... 10   inf
// num = 20 .... 10   20
// num = 2  .... 2    20  // we will not change 20 .. it will preserve prev duplet value.
// num = 30 .... 30 > 20 ... return true.


class Solution {
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for(int num : nums){
            if(num <= a) a = num;
            else if(num <= b) b = num;
            else return true;
        }
        return false;
    }
}
