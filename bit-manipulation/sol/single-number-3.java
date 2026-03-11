// --------------------- OPTIMAL : Bucket Method ------------------
// TC -> N
// SC -> 1

class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int a : nums) xor = xor ^ a;

        // extract the right most set bit from the xor.
        int mask = (xor & (xor-1))^xor;

        int bucket1 = 0; // on bit container
        int bucket2 = 0; // off bit container.

        for(int a : nums){
            if((a & mask) == 0){
                bucket1 = bucket1 ^ a;
            }else{
                bucket2 = bucket2 ^ a;
            }
        }

        return new int[]{bucket1 , bucket2};
    }
}
