// time : N
// space : 1
// 'i' will traverse the array .. when 'i' points to the first element in the duplicated .. fill the arr.
// 'idx' will fill the same arr.

class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int idx = -1;
        int n = nums.length;
        while(i < n){
            idx++;
            nums[idx] = nums[i];

            // skip the duplicates at i.
            i++;
            while(i < n && nums[i] == nums[i-1]) i++;
        }
        return idx + 1;
    }
}
