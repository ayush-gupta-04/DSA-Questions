// Time Complexity: O(2n * k)
//   - For each of the 2n subsequences, storing takes O(k) time where k is the average length of each combination.
// Space Complexity: O(k * x)
//   - To store all x valid combinations, each of average length k.

import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    void fun(int[] nums,int target,int i,List<Integer> list){
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        if(i == nums.length){
            return;
        }
        // take;
        if(target >= nums[i]){
            list.add(nums[i]);
            fun(nums,target - nums[i],i + 1,list); // take the element only once.
            list.remove(list.size() - 1);
        }
        // not - take ... then don't any instance of it.
        int j = i;
        j++;
        while(j < nums.length && nums[j] == nums[j - 1]){
            j++;
        }
        fun(nums,target,j,list);
    }

    public List<List<Integer>> combinationSum2(int[] nums,int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        fun(nums,target,0,list);
        return res;
    }
}
