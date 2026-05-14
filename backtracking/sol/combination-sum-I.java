//Time Complexity: O(2^t * k) due to exploring all combinations up to the target with copying each valid combination of average length k.
//Space Complexity: O(k * x) to store all valid combinations, where x is the number of combinations and k is their average length.

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    void fun(int[] nums,int i,int k,List<Integer> list){
        // whenver k == 0 .. i don't need to go to the end... just return the ans from here.
        if(k == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        // if i found no ans during the middle .. and reached the end .. just return.
        if(i == nums.length){
            return;
        }
        // not take case;
        fun(nums,i + 1,k,list);
        // take only if i can.
        if(k >= nums[i]){
            // since hum iss call m jaa rhe h .. taking the element ith.
            // i should remove the same element.
            list.add(nums[i]);
            fun(nums,i,k - nums[i],list);
            list.remove(list.size() - 1);
        }
        return;
    }

    public List<List<Integer>> combinationSum(int[] nums,int k) {
        List<Integer> vec = new ArrayList<>();
        fun(nums,0,k,vec);
        return result;
    }
}
