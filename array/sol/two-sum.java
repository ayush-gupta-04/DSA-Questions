// ---------------- Better Approach : Using HashMap ----------------
// time : N
// Space : N
// If sorted then we would have used 2 Pointers.
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];
            if (numMap.containsKey(rem)) {
                return new int[] { numMap.get(rem), i };
            }
            numMap.put(nums[i], i);
        }
        return new int[] {-1,-1};
    }
}
