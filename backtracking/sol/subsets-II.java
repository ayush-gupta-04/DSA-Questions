// TC -> 2^N (recursion)
// SC -> N (recursive stack space)
import java.util.*;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    void fun(int[] nums, int i, List<Integer> list) {
        if (i == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        // let nums[i] = a;
        // take 'a'.
        list.add(nums[i]);
        fun(nums, i + 1, list);
        list.remove(list.size() - 1);

        // not take 'a' . . . skip duplicates.
        i++;
        while (i < nums.length && nums[i - 1] == nums[i]) {
            i++;
        }
        fun(nums, i, list);
        return;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> vec = new ArrayList<>();
        fun(nums, 0, vec);
        return result;
    }
}
